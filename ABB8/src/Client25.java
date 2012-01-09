import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Client25 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int classifierNum = 1000;  // number of classifiers
		
		ArrayList<Dataset> training = new Reader("ring.csv").getDatasets();
		
		ArrayList<Classifier> classifier = new ArrayList<Classifier>();
		for(int i=0; i<10; i++){
			classifier.add( new Classifier(training) ); // adds new random classifier
		}

	}

}
