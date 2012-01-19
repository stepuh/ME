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

			double [] richtung = c.v.times(100).getArray()[0];
			double [] start = { c.xStart, c.yStart };
			double [] end1 = { start[0]+richtung[0], start[1]+richtung[1] };
			double [] end2 = { start[0]-richtung[0], start[1]-richtung[1] };
			g.setColor (Color.LIGHT_GRAY);
			g.fillRoundRect((int)start[0], (int)start[1], 10, 10, 5, 5);
			g.drawLine((int)start[0], (int)start[1], (int)end1[0], (int)end1[1]);
			g.drawLine((int)start[0], (int)start[1], (int)end2[0], (int)end2[1]);

			last = c;
		}
		
		double [] richtung = last.v.times(20).getArray()[0];
		double [] start = { last.xStart, last.yStart };
		double [] end1 = { start[0]+richtung[0], start[1]+richtung[1] };
		double [] end2 = { start[0]-richtung[0], start[1]-richtung[1] };
		g.setColor (Color.GREEN);
		g.fillRoundRect((int)start[0], (int)start[1], 10, 10, 5, 5);
		g.drawLine((int)start[0], (int)start[1], (int)end1[0], (int)end1[1]);
		g.drawLine((int)start[0], (int)start[1], (int)end2[0], (int)end2[1]);
		
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
