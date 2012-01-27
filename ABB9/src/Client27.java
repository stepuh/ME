import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Client27 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
		// read data
		ArrayList<Pattern> unfiltered = new Reader( "digits-testing-neu.txt" ).getDatasets();

		// drop weird patterns
		ArrayList<Pattern> training = new ArrayList<Pattern>();
		for(Pattern p: unfiltered){
			if( p.teaching != -1){
				training.add( p );
			}
		}
		
		NMF myNMF = new NMF(training, 20);
		
		myNMF.iterate(500);
		myNMF.finalNormalizeW();
		
		
		//training = myNMF.getWPatterns();
		training = myNMF.getWPatterns();
			
		int offset_top = -1;
		int offset_left = 0;
		int i=0;
		for(Pattern p: training){
			p.features.print(1, 1);
			i++;
			if(i>20){
				break;
			}
			JFrame jf = new JFrame();
			jf.setBounds(160, 230, 150, 200);
			DrawingPanel panel = new DrawingPanel(p);
			jf.add(panel);
			jf.repaint();
			jf.setVisible(true);
			

		}

	}

}
