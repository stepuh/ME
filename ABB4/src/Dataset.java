public class Dataset {
	
	int calculatedKlass;
	int correctKlass;
	double[] features;						
	
	public Dataset( double [] f){
		features = f;
	}
	
	public Dataset( int k, double[] f){
		correctKlass = k;
		features = f;
	}
}
