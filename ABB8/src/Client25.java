import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Client25 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		int iterationNum = 4; // == committee size
		int classifierNum = 100000;  // number of classifiers
		

		
		ArrayList<Pattern> training = new Reader( "ring.csv" ).getDatasets();
		
		// features normalisieren
		// minimum und maximum finden
		double minimum=0;
		double maximum=1;
		for( Pattern p: training ){
			double[] f= p.features.toArray();
			for( double d: f){
				if( d<minimum){
					minimum = d;
				}
				if( d>maximum){
					maximum = d;
				}
			}
		}
		// werte auf 0 shiften
		for( Pattern p: training ){
			double[] f = p.features.toArray();
			for(int i=0; i<f.length; i++){
					f[i] -= minimum;
			}
			p.features = new Vector(f);
		}
		// werte auf 1 normalisieren
		maximum -= minimum;
		for( Pattern p: training){
			double[] f = p.features.toArray();
			for(int i=0; i<f.length; i++){
					f[i] /= maximum;
			}
			p.features = new Vector(f);
		}
		
		
		// classifier initialisieren
		ArrayList<Classifier> classifiers = new ArrayList<Classifier>();
		for( int i=0; i<classifierNum; i++ ){
			classifiers.add( new Classifier( training ) ); // adds new random classifier
		}

		
		AdaBoost boostIt = new AdaBoost(classifiers, iterationNum, training);
		ArrayList<Classifier> committee = boostIt.committee;
		
		JFrame jf = new JFrame();
		jf.setBounds(500, 500, 500, 500);
		jf.setBackground(Color.BLACK);
		
		DrawingPanel panel = new DrawingPanel(training, committee);
		jf.add(panel);
		jf.repaint();
		jf.setVisible(true);
		
		
		
		int correct=0;
		for( Pattern p: training ){
			if( boostIt.classify( p ) == p.teaching ){
				correct++;
			}
		}
		System.out.println(correct/(double)training.size());

	}

}
