


/*
 * A Container actually just a point in space with <dim> dimensions.
 * It can be either a prototype or a dataset.
 * It also has a absolute value correctKlass and a temporarily value
 * calculated class that is valid in only one specific round of
 * the expectation maximization algorithms.
 */
public class Pattern {
	
	Vector teaching;				
	Vector features;				// position vector
	int dim;						// dimensions of vector
	
	
	public Pattern( double [] f){
		features = new Vector(f);
		dim = f.length;

	}
	
	public Pattern( int k, double[] f){ // 0-9
		this(f);
		double[] zer = {1,0,0,0,0,0,0,0,0,0};
		double[] one = {0,1,0,0,0,0,0,0,0,0};
		double[] two = {0,0,1,0,0,0,0,0,0,0};
		double[] thr = {0,0,0,1,0,0,0,0,0,0};
		double[] fou = {0,0,0,0,1,0,0,0,0,0};
		double[] fiv = {0,0,0,0,0,1,0,0,0,0};
		double[] six = {0,0,0,0,0,0,1,0,0,0};
		double[] sev = {0,0,0,0,0,0,0,1,0,0};
		double[] eig = {0,0,0,0,0,0,0,0,1,0};
		double[] nin = {0,0,0,0,0,0,0,0,0,1};
		double[][] numbers = new double[10][10];
		numbers[0] = zer;
		numbers[1] = one;
		numbers[2] = two;
		numbers[3] = thr;
		numbers[4] = fou;
		numbers[5] = fiv;
		numbers[6] = six;
		numbers[7] = sev;
		numbers[8] = eig;
		numbers[9] = nin;
		this.teaching = new Vector( numbers[k] );
	}
	
	public Pattern( Vector teaching, double[] f){
		this(f);
		this.teaching = teaching;
	}

	

}
