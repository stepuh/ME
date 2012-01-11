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
		
		
		for(Pattern p: points){
			int mid_x = (int)(p.features.toArray()[0] * 500);
			int mid_y = (int)(p.features.toArray()[1] * 500);
			int color = p.teaching;
			if(-1 == color){
				g.setColor (Color.BLUE);
			}
			else if( 1 == color){
				g.setColor (Color.RED);
			}else{
				g.setColor (Color.BLACK);
			}
			g.drawRect(mid_x, mid_y, 1, 1);
		}
		
		for(Classifier c: committee){
			g.setColor(Color.WHITE);
			double[] vector = c.v.toArray();
			int xStart = (int) (c.xStart * 500);
			int yStart = (int) (c.yStart * 500);
			int xEnd = (int) ((c.xStart + vector[1]) * 500);
			int yEnd = (int) ((c.yStart + vector[0]) * 500);
			g.fillRect(xStart, yStart, 5, 5);
			g.drawLine(xStart, yStart, xEnd, yEnd);
			
			xEnd = (int) ((c.xStart - vector[1]) * 500);
			yEnd = (int) ((c.yStart - vector[0]) * 500);
			g.drawLine(xStart, yStart, xEnd, yEnd);
		}
	}

}
