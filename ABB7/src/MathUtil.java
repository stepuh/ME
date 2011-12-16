
public class MathUtil {
	
	// Sigmoid for single value x, c = 1
	static double sigmoid( double x ){
		return( 1/(1-Math.exp(-x)));
	}
	
	// Sigmoid for double array
	static double[] sigmoid( double[] x ){
		double[] result = new double[x.length];
		for(int i=0; i<result.length; i++){
			result[i] = MathUtil.sigmoid(x[i]);
		}
		return result;
	}
	
	// Sigmoid in Vector flavour
	static Vector sigmoid( Vector x){
		Vector result = new Vector( sigmoid(x.toArray() ) );
		return result;
	}
	

}
