import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {
	

	public static void main(String[] args) throws FileNotFoundException{
		int k = 10;
		
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();
		
		Database db = new Database(training);
		
		KMeans algo = new KMeans( db, k );
		algo.run();
		
		Dataset protoA = db.prototypes.get(0);
		Dataset protoB = db.prototypes.get(1);
		
		//Fischer f = new Fischer(db);
		
		//double[] smallOmega = f.getW(protoA, protoB);
		
		for(Relation r: db.relations){
			r.probability = 1.0/db.prototypes.size();
		}
		
		LinearAlgebra.calculateExpectation(db.relations.get(0));

	}
}
