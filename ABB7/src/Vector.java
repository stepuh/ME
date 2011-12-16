import Jama.Matrix;


public class Vector extends Matrix{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6689309509600773428L;

	public Vector( double[] v){
		super(v,1);
	}
	
	public Vector( double[][] v){
		super(v[0],1);
	}
	
	public Vector( Matrix v){
		super(v.getArray()[0],1);
	}



	public double[] toArray(){
		double[][] tmp = this.getArray();
		return tmp[0];
	}
	
	public Vector getExtended(){
		double[] inputArr = this.toArray();
		double[] inputExtArr = new double[inputArr.length+1];
		for(int i=0; i<inputArr.length; i++){
			inputExtArr[i] = inputArr[i];
		}
		inputExtArr[inputArr.length] = 1;
		return new Vector(inputExtArr);
	}
}
