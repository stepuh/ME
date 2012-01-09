import java.util.ArrayList;


public class Classifier {


	public double corrects;
	public double xStart;
	public double yStart;
	public Vector v;
	
	
	public Classifier(ArrayList<Pattern> patterns){
		// Create random vector for classification 
		xStart = Math.random();
		yStart = Math.random();
		double x2 = Math.random();
		double y2 = Math.random();
		
		double[] vArray = new double[2];
		vArray[0] = xStart - x2;
		vArray[1] = yStart - y2;
				
		v = new Vector(vArray);
		
		// Test perzeptron on all Datasets
		corrects = 0;
		for(Pattern p : patterns){
			// poject vector of pattern on classifier's vector
			int side = 0;
			if (vArray[0] * p.features.toArray()[1] - vArray[1]* p.features.toArray()[0] < 0){
				// links
				side = 1;
			}
			
			// compare result with teaching
			if(side == p.teaching){
				corrects++;
			}
			
		}		
		
		System.out.println("corrects: " + corrects);
	}
	
}
