import java.io.FileNotFoundException;
import java.util.ArrayList;

import Jama.Matrix;


// Hallo Steffen!! :)
public class Client {
	
	public static ArrayList<Dataset> generatePrototypes(int n, int featureLength){
		ArrayList<Dataset> prototypes = new ArrayList<Dataset>();
		for(int i=0; i<n; i++){
			double[] features = new double[featureLength];
			for(int j=0; j<featureLength; j++){
				features[j] = Math.random() *100;
			}
			prototypes.add( new Dataset(features) );
		}
		return prototypes;
	}

	public static void main(String[] args) throws FileNotFoundException{
		int k = 10;
		int dimensions;
		
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();
		
		Database db = new Database(training);
		dimensions = db.dimensions;
		
		
		db.addAllPrototypes( generatePrototypes( k, dimensions ));
		double count = db.datasets.size();
		for(Relation r: db.relations){
			r.probability = 1/count;
		}
		
		Matrix ko = new Matrix( LinearAlgebra.getS(db.prototypes.get(1)));
		ko.print(16, 4);
	}
}
