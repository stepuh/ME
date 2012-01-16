import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;


public class DrawingPanel extends JPanel{
	
	ArrayList<Pattern> points;
	ArrayList<Classifier> committee;
	
	public DrawingPanel( ArrayList<Pattern> points, ArrayList<Classifier> committee ){
		this.points = points;
		this.committee = committee;
	}

	
	public void paintComponent(Graphics g) {
	
		Classifier last= null;
		
		for(Classifier c: committee){
			g.setColor(Color.WHITE);
			double[] vector = c.v.toArray();
			int xStart = 500 - (int) (c.xStart);
			int yStart = 500 - (int) (c.yStart);
			g.fillRect(xStart, yStart, 5, 5);

			int xEnd = (int) (xStart + vector[0] * 2);
			int yEnd = (int) (yStart + vector[1] * 2);
			g.drawLine(xStart, yStart, xEnd, yEnd);
			
			xEnd = (int) (xStart - vector[0] * 2);
			yEnd = (int) (yStart - vector[1] * 2);
			g.drawLine(xStart, yStart, xEnd, yEnd);
			last = c;
		}
		
		for(Pattern p: points){
			int mid_x = (int)(p.features.toArray()[0]);
			int mid_y = (int)(p.features.toArray()[1]);
			int color = p.teaching;
			if(-1 == color){
				if ( last.classify(p) == 1){
					g.setColor (Color.BLUE);
				}else{
					g.setColor(Color.CYAN);
				}
			}
			else if( 1 == color){
				if ( last.classify(p) == 1){
					g.setColor (Color.RED);
				}else{
					g.setColor( Color.MAGENTA );
				}
			}else{
				g.setColor (Color.BLACK);
			}
			g.drawRect(mid_x, mid_y, 1, 1);
		}
	}

}
