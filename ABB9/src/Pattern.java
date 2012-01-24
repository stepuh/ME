import Jama.Matrix;




/*
 * A Container actually just a point in space with <dim> dimensions.
 * It can be either a prototype or a dataset.
 * It also has a absolute value correctKlass and a temporarily value
 * calculated class that is valid in only one specific round of
 * the expectation maximization algorithms.
 */
public class Pattern {
	
	int teaching;				
	Vector features;				// position vector
	Matrix visualMatrix;
	int dim;						// dimensions of vector
	double weight;

	
	public Pattern( double [] f){
		features = new Vector(f);
		dim = f.length;

	}
	
	public Pattern( int k, double[] f){ // 0-9
		this(f);
		this.teaching = k;
	}
}
