import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Client25 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int iterationNum = 100; // == committee size
		int classifierNum = 10000;  // number of classifiers
		

		
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
					f[i] *= 500;
					f[i] += 100;
			}
			p.features = new Vector(f);
		}
		
		
		// classifier initialisieren
		ArrayList<Classifier> classifiers = new ArrayList<Classifier>();
		for( int i=0; i<classifierNum; i++ ){
			classifiers.add( new Classifier( training ) ); // adds new random classifier
		}

		JFrame jfall = new JFrame();
		jfall.setBounds(500, 100, 700, 700);
		jfall.setBackground(Color.BLACK);
		
		AdaBoost boostIt = new AdaBoost(classifiers, iterationNum, training);
		for(int i=0; i < iterationNum; i++ ){
			boostIt.iterate();
			ArrayList<Classifier> committee = boostIt.committee;
			
			// print the rate of correct classifications for every iteration
			int correct=0;
			for( Pattern p: training ){
				if( boostIt.classify( p ) == p.teaching ){
					correct++;
				}
			}
			System.out.println(i+" "+correct/(double)training.size());
			
			// draw the panel for the first 5 iterations
			if(i <= 5){
				JFrame jf = new JFrame();
				jf.setBounds(500, 100, 700, 700);
				jf.setBackground(Color.BLACK);
				DrawingPanel panel = new DrawingPanel(training, committee);
				jf.add(panel);
				jf.repaint();
				jf.setVisible(true);
			}
		}
	}
}
