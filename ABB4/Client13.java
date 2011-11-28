import java.util.ArrayList;


public class Client13 {

	public static void main(String[] args) throws Exception{
		// reads train data out of file
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();
		
		Database db = new Database(training);
		KNN myKNN = new KNN(db);
				
		int k = 3;
		
		// test for all classes with k-nearest-neighbors 
		System.out.println("Runing KNN. Please wait!");
		for(int c=0; c< 10; c++){
			System.out.println( "Klasse "+c+" Erfolgsrate: "+myKNN.testClass(c,k) );
		}
	}

}
