import java.util.ArrayList;

public class Client {
	
	
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
		f.getW(db.prototypes.get(0), db.prototypes.get(1));
		

	}
}
