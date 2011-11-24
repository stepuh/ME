import java.util.ArrayList;


public class Client10 {
	
	public static void main(String[] args) throws Exception{
		// reads train data out of file
		

		
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();
		
		Database db = new Database(training);
		int k = 12;
		Knn kNN= new Knn(db, k);

		kNN.run();
		
		
		// init statistic variables
		int[] inClass = new int[k];
		int[] foundInClass = new int[k];
		
	
		
		// print it
	
	}

}
