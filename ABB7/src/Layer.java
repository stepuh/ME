import Jama.Matrix;


public class Layer {

	public Matrix wExt;
	public Matrix derivations;
	public Vector delta;
	public Vector o;
	public int k;
	
	
	public Layer(int prevK, int k){
		this.k = k;

		// Matrix erstellen
		double[][] tmpWExt = new double[prevK][k];
		for(int i = 0; i < prevK; i++){
			for(int j = 0 ; j < k; j ++){
				tmpWExt[i][j] = Math.random() * 2 -1; // [-1,1]
			}
		}
		wExt = new Matrix(tmpWExt);		
	}

	
	
	// w is the submatrix of wExt without the n+1th row
	public Matrix getW(){
		int rowCount = wExt.getRowDimension();
        int columnCount = wExt.getColumnDimension();
        double[][] wExtArr = wExt.getArray();
        double[][] w = new double[rowCount-1][columnCount];
        for(int i=0; i<rowCount-1; i++){
                w[i] = wExtArr[i];
        }
        return new Matrix(w);	
	}
	
	
	
	public Vector calc( Vector input ){ // calculates o and derivatives
		
		// extend input to input^
		double[] inputArr = input.toArray();
		double[] inputExtArr = new double[inputArr.length+1];
		for(int i=0; i<inputArr.length; i++){
			inputExtArr[i] = inputArr[i];
		}
		inputExtArr[inputArr.length] = 1;
		Vector inputExt = new Vector(inputExtArr);
		
		// calculate o = sigmoid(o^ * w- )
		Vector tmp = new Vector( inputExt.times(wExt) );
		o = MathUtil.sigmoid( tmp );
		
		// calculate D_j: for diagnoal axis oi^j * (1- oi^j)
		double[] oArr = o.toArray();
		derivations = new Matrix(oArr.length, oArr.length);
		for(int i=0; i<oArr.length; i++){
			derivations.set(i, i, oArr[i]*(1-oArr[i]));
		}
		
		// return o
		return o;
	}
	
	
	public Vector calcAndSave( Vector input ){
		calc(input);
		return null;
	}
}
