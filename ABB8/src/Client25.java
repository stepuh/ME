import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Client25 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int adaBoostIterationNum = 10; // == committee size
		int classifierNum = 1000;  // number of classifiers
		
		ArrayList<Dataset> training = new Reader( "ring.csv" ).getDatasets();
		// correctClass bereinigen
		for( Dataset d:training ){
			if( -1 == d.correctKlass ){
				d.correctKlass = 0;
			}
		}	
		
		
		ArrayList<Classifier> classifier = new ArrayList<Classifier>();
		for( int i=0; i<classifierNum; i++ ){
			classifier.add( new Classifier( training ) ); // adds new random classifier
		}

	}

}
