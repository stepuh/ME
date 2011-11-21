import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Client {
	

	public static void main(String[] args) throws FileNotFoundException{
		int k = 10;
		
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();
		
		Database db = new Database(training);
		
		KMeans algo = new KMeans( db, k );
		algo.run();
	}
}
