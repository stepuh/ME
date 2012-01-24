import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Client26 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		// read data
		ArrayList<Pattern> unfiltered = new Reader( "digits-testing-neu.txt" ).getDatasets();

		// drop weird patterns
		ArrayList<Pattern> training = new ArrayList<Pattern>();
		for(Pattern p: unfiltered){
			if( p.teaching != -1){
				training.add( p );
			}
		}
		int offset_top = -1;
		int offset_left = 0;
		for(int i=0; i<30; i++){
			JFrame jf = new JFrame();
			if( 0 == i%8 ){
				offset_top++;
				offset_left = 0;
			}

			jf.setBounds(offset_left*160, offset_top*230, 150, 200);
			jf.setBackground(Color.RED);
			DrawingPanel panel = new DrawingPanel(training.get(i));
			jf.add(panel);
			jf.repaint();
			jf.setVisible(true);
			offset_left++;
		}
	
	
	
		
	
	}

}
