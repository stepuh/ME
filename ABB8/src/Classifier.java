	import java.util.ArrayList;


public class Classifier {


	public double corrects;
	public double xStart;
	public double yStart;
	public Vector v;
	public double weight;
	
	public ArrayList<Integer> results;
	
	
	public Classifier(ArrayList<Pattern> patterns){
		// Create random vector for classification 
		xStart = Math.random()*500+100;
		yStart = Math.random()*500+100;
		double x2 = Math.random()*500+100;
		double y2 = Math.random()*500+100;
		
		double[] vArray = new double[2];
		vArray[0] = xStart - x2;
		vArray[1] = yStart - y2;
		v = new Vector(vArray);
		
		// Test perzeptron on all Datasets
		corrects = 0;
		for(Pattern p : patterns){
			// compare classification with teaching
			if(classify(p) == p.teaching){
				corrects++;
			}
			
		}		
	
		// Identify liers
		if(corrects < patterns.size()/2){
			// turn vector by 180 degrees so result is swaped
			double xTmp = x2;
			double yTmp = y2;
			x2 = xStart;
			y2 = yStart;
			xStart = xTmp;
			yStart = yTmp;
			
			vArray[0] = xStart -x2;
			vArray[1] = yStart -y2;
			v = new Vector(vArray);
		}

		
		// classify again and save results
		corrects = 0;
		results = new ArrayList<Integer>();
		for(Pattern p : patterns){
			// compare classification with teaching
			int side = classify(p);
			results.add(side);
			
			if(classify(p) == p.teaching){
				corrects++;
			}
		}	
	}
	
	
	public int classify(Pattern p){
		double p1 = p.features.toArray()[0];
		double p2 = p.features.toArray()[1];
		double s1 = this.xStart;
		double s2 = this.yStart;
		double g1 = this.v.toArray()[0];
		double g2 = this.v.toArray()[1];
		if(g2*(p1-s1)-g1*(p2-s2) > 0 ){
			return 1;
		}else{
			return -1;
		}

	}
	
	
}
