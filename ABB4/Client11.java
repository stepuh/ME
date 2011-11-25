import java.util.ArrayList;

import Jama.Matrix;

public class Client11 {
	
	
	public static void main(String[] args) throws Exception{
		// reads train data out of file
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();

		
		Database db = new Database(training);
		
		// we want to have k clusters
		int k = 10;
		
		// initializes the clusters by running k-means
		KMeans km = new KMeans(db, k);
		km.rounds = 10;
		km.run();
		

		// initialize so we can use Gauss

		
		db.initGauss();

		// calculate fischer's discriminant
		Fischer f = new Fischer(db);
		System.out.println("Running Fischers Discriminant. Please wait!");
		for(Prototype p: db.prototypes){
			int erfolge=0;
			
			// get w
			Matrix w = new Matrix(f.getW(p),1);
			// projeziere die myus auf w
			Matrix myuA = new Matrix( p.features, 1);
			Matrix myuB = new Matrix( f.getGegenMyu(p), 1);
			double myuAproj = myuA.transpose().times(w).getArray()[0][0];
			double myuBproj = myuB.transpose().times(w).getArray()[0][0];
			
			boolean AgroesserB = (myuAproj > myuBproj);

			// schwellwert in easy
			double schwell = ((myuAproj + myuBproj)/2);
			
			
			for(Dataset d: db.datasets){
				boolean klass = f.getKlass(AgroesserB, schwell, w, d);
				if(klass && p.correctKlass == d.correctKlass){
					erfolge++;
				}
			}
			System.out.println("Prototype "+p.correctKlass+" - Erfolgsrate:"+(double)erfolge/db.datasets.size());
			
		}

	}
}
