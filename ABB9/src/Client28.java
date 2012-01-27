import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Client28 {

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
		ArrayList<Pattern> reconstructed = new ArrayList<Pattern>();
		reconstructed = myNMF.getVStrichPatterns();
			

		int item=10;

		JFrame jfre = new JFrame();
		jfre.setBounds(160, 230, 150, 200);
		DrawingPanel panelre = new DrawingPanel(reconstructed.get(item));
		jfre.add(panelre);
		jfre.repaint();
		jfre.setVisible(true);
		
		Thread.sleep(100);
		
		JFrame jf = new JFrame();
		jf.setBounds(160, 230, 150, 200);
		jf.setBackground(Color.WHITE);
		DrawingPanel panel = new DrawingPanel(training.get(item));
		jf.add(panel);
		jf.repaint();
		jf.setVisible(true);
		
		
			

		

	}

}
