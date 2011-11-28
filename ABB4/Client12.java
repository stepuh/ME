import java.util.ArrayList;


public class Client12 {
	
	public static void main(String[] args) throws Exception{
		// reads train data out of file
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();

		
		Database db = new Database(training);
		LinearModel lm = new LinearModel(db);
		
		// Teste f�r alle Klassen
		System.out.println("Running Linear Klassificator. Please wait!");
		for(int k=0; k< 10; k++){
			System.out.println( "Klasse "+k+" Erfolgsrate: "+lm.testClass(k) );
		}
	}

}
