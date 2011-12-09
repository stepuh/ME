public class Dataset {
	
	int correctKlass;				// prototype: class of most members; dataset: represented class 
	double[] features;				// position vector
	
	public Dataset( double [] f){
		features = f;
	}
	
	public Dataset( int k, double[] f){
		this(f);
		correctKlass = k;
	}

	

}
