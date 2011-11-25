import java.util.ArrayList;
import java.util.Arrays;

import Jama.Matrix;



public class Fischer {
	Database db;
	
	
	Fischer( Database db){
		this.db = db;
	}
	

	
	private Matrix getGegenS(Prototype p){
		// Gegenklasse der Members finden
		ArrayList<Dataset> nonMembers = new ArrayList<Dataset>();
		for(Dataset d: db.datasets){
			if( d.getMostProbable() != p ){
				nonMembers.add(d);
			}
		}
		double tempmyuB[] = getGegenMyu(p);
		
		Matrix kovarB = new Matrix(db.dimensions, db.dimensions);

		double norm = 0;
		for(Dataset d: nonMembers){
			for(Relation r: d.relations){
				if( r.prototype != p){
					norm += r.probability;
					
					double[] dist = new double[db.dimensions];			
					for(int i=0; i<db.dimensions; i++){
						dist[i] = r.dataset.features[i] - tempmyuB[i];
					}
					
					Matrix distMatrix = new Matrix(dist,1);
					Matrix distMatrixT = distMatrix.transpose();
					kovarB = kovarB.plus(distMatrixT.times(distMatrix));
					kovarB = kovarB.times(r.probability);
				}
			}
		}
		return kovarB.times(1.0/norm);

	}
	
	private double[] getGegenMyu(Prototype p){
		// Gegenklasse der Members finden
		ArrayList<Dataset> nonMembers = new ArrayList<Dataset>();
		for(Dataset d: db.datasets){
			if( d.getMostProbable() != p ){
				nonMembers.add(d);
			}
		}
		// myu
		return LinearAlgebra.getMyu(nonMembers);
		
	}
	
	// Returns vector w so that the projected clusters of Prototype A and Prototype B
	// on w are well parted and have minimal kovarianz
	public double[] getW(Prototype p){
		
		// Kovarianzmatrizen
		Matrix kovarA = p.s;
		Matrix kovarB = getGegenS(p);
		
		// Myus
		Matrix myuA = new Matrix( p.features, 1);
		double tempmyuB[] = getGegenMyu(p);
		Matrix myuB = new Matrix(tempmyuB,1);

		
		Matrix sum = kovarA.plus(kovarB).inverse();
		Matrix diff = myuA.minus(myuB).transpose();
		
		// get 2-dim array as temporarily result
		double[][] tmpResult= sum.times(diff).getArray();
		
		// convert into 1 dimensional array as final result
		double[] w = new double[p.dim];
		for(int i = 0; i < p.dim; i++){
			w[i] = tmpResult[i][0];
		}
		System.out.println("result vector: " + Arrays.toString(w));
		
		return w;
		
	}
	
	
	public boolean getKlass(Prototype p, Dataset d){
		
		
		Matrix w = new Matrix( getW(p),1);
		
		// projeziere die myus auf w
		Matrix myuA = new Matrix( p.features, 1);
		Matrix myuB = new Matrix(getGegenMyu(p), 1);
		double myuAproj = myuA.transpose().times(w).getArray()[0][0];
		double myuBproj = myuB.transpose().times(w).getArray()[0][0];
		
		// welches ist grš§er? 1: proto, 2: gegenklasse

		// schwellwert in easy
		double schwell = ((myuAproj + myuBproj)/2);
		
		// projeziere d auf w und prŸfe den schwellwert!
		Matrix dVector = new Matrix(d.features,1);
		double dProj = dVector.transpose().times(w).getArray()[0][0];
		
		if( dProj > schwell){
			if(myuAproj > myuBproj){
				return false;
			}else{
				return true;
			}
		}else{
			if(myuAproj > myuBproj){
				return true;
			}else{
				return false;
			}
		}

	}

}
