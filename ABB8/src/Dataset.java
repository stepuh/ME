

/*
 * A Container actually just a point in space with <dim> dimensions.
 * It can be either a prototype or a dataset.
 * It also has a absolute value correctKlass and a temporarily value
 * calculated class that is valid in only one specific round of
 * the expectation maximization algorithms.
 */
public class Dataset {
	
	int correctKlass;				// prototype: class of most members; dataset: represented class 
	double[] features;				// position vector
	int dim;						// dimensions of vector
	double weight;					// weight needed for AdaBoost
	
	
	public Dataset( double [] f){
		features = f;
		dim = features.length;

	}
	
	public Dataset( int k, double[] f){
		this(f);
		correctKlass = k;
	}

	

}
