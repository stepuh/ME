import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;


public class DrawingPanel extends JPanel{
	
	double[] features;
	
	public DrawingPanel( Pattern p ){
		this.features = p.features.toArray();
	}

	

	public void paintComponent(Graphics g) {
		int valuesInRow = 12;
		int offset_left = 15;
		int offset_top = 15;
		
		int rowCount = 0;
		for( int i=0; i<features.length; i++){
			rowCount = i/valuesInRow;
			float grauwert = (float) features[i];
			Color grauton = new Color( grauwert, grauwert, grauwert );
			g.setColor (grauton);
			g.fillRect(offset_left+10*(i%valuesInRow), offset_top+10*rowCount, 10, 10);
		}
		

	}

}
