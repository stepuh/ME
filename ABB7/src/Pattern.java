


/*
 * A Container actually just a point in space with <dim> dimensions.
 * It can be either a prototype or a dataset.
 * It also has a absolute value correctKlass and a temporarily value
 * calculated class that is valid in only one specific round of
 * the expectation maximization algorithms.
 */
public class Pattern {
	
	int teaching;				
	double[] features;				// position vector
	int dim;						// dimensions of vector
	
	
	
	public Pattern( double [] f){
		features = f;
		dim = features.length;

	}
	
	public Pattern( int k, double[] f){
		this(f);
		teaching = k;
	}

	

}
