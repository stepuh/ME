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
		
		ArrayList<Pattern> training = new Reader( "ring.csv" ).getDatasets();
		
		// teaching bereinigen
		for( Pattern p: training ){
			if( -1.0 == p.teaching ){
				p.teaching = 0;
			}
		}
		
		// minimum auf 0 shiften
		double minimum;
		for( Pattern p: training ){
			double[] f= p.features.toArray();
			for( double d: f){
				if( d<minimum){
					minimum = d;
				}
			}
		}
		for( Pattern p: training ){
			double[] f = p.features.toArray();
		}
		
		
		
		ArrayList<Classifier> classifier = new ArrayList<Classifier>();
		for( int i=0; i<classifierNum; i++ ){
			classifier.add( new Classifier( training ) ); // adds new random classifier
		}

	}

}
