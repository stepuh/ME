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
	
	
	
	public Vector calc( Vector input ){ // without Ext
		double[] inputArr = input.toArray();
		// TODO:
		return null;
	}
	
	
	public Vector calcAndSave( Vector input ){
		calc(input);
		return null;
	}
}
