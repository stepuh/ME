import java.util.ArrayList;


public class Client9 {
	
	public static void main(String[] args) throws Exception{
		// reads train data out of file
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();

		
		Database db = new Database(training);
		LinearModel lm = new LinearModel(db);
		
		// Teste für alle Klassen
		for(int k=1; k<= 10; k++){
			System.out.println( "Klasse "+k+" Erfolgsrate: "+lm.testClass(1) );
		}
	}

}
