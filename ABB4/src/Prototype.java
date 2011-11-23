import java.util.ArrayList;

import Jama.Matrix;


public class Prototype extends Container{


	double pi;
	Matrix s;
	
	public Prototype(double[] f) {
		super(f);
	}
	
	public Prototype(int c, double[] f) {
		super(c, f);
	}
	
	public ArrayList<Dataset> getRelated(){
		ArrayList<Dataset> result = new ArrayList<Dataset>();
		for(Relation r: relations){
			result.add(r.dataset);
		}
		return result;
	}
	
	public void calcPi(){
		pi = 0;
		int size = relations.size();
		for(Relation r: relations)
			pi += r.probability;
		pi /= size;
	}
	
	public void calcS(){
		s = new Matrix(dim, dim);
		
		double[] myu = LinearAlgebra.getMyu( this.getRelated() );
		
		// sum over relations to calculate S
		double sum = 0;
		for(Relation r: relations){
			sum += r.probability;
			
			double[] dist = new double[dim];			
			for(int i=0; i<dim; i++){
				dist[i] = r.dataset.features[i] - myu[i];
			}
			
			Matrix distMatrix = new Matrix(dist,1);
			Matrix distMatrixT = distMatrix.transpose();
			s = s.plus(distMatrixT.times(distMatrix));
			s = s.times(r.probability);
		}	
		
		s = s.times(1.0/sum);
	}

}
