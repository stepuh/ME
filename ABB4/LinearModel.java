import Jama.Matrix;


public class LinearModel {
	Database db;
	Matrix x;
	
	
	LinearModel( Database db ){
		this.db = db;
		this.calcX();
	}
	
	
	
	// Calculates and returns the matrix X 
	private void calcX(){
		// get X
		double[][] x = new double[ db.datasets.size() ] [ db.dimensions ];
		for(int i=0; i<db.datasets.size(); i++){
			double[] features = db.datasets.get(i).features;
			for(int j=0; j<db.dimensions; j++){
				x[i][j] = features[j];
			}
		}
		this.x = new Matrix( x );
	}
	
	
	
	
	
	public double testClass(int c){
		
		// get y by parsing each datasets correct class and comparing to c
		double [] yArray = new double[db.datasets.size()];
		for(int i=0; i<db.datasets.size(); i++){
			Dataset d = db.datasets.get(i);
			yArray[i] = (d.correctKlass == c) ? 1 : -1;
		}
		Matrix y = new Matrix(yArray,1 ).transpose();
		
		// get a = (xT * x)^{-1} * xT * y
		Matrix xT = x.transpose();
		Matrix tmp = xT.times(x); // Matrix is singular :(
		Matrix left = tmp.inverse();
		Matrix right = xT.times(y);
		Matrix a = left.times(right);
		
		// Do the test for every Dataset
		int inClass=0;
		int foundInClass=0;
		
		for(Dataset d: db.datasets){
			Matrix checkMe = new Matrix( d.features, 1);
			// should we find this?
			if(d.correctKlass == c){
				inClass++;
			}
			// do we find this?
			Matrix val = a.transpose().times(checkMe.transpose());
			double valScalar = val.getArray()[0][0]; // should be 1x1 Matrix
			if( 0 < valScalar){
				foundInClass++;
			}	
		}
		return (double) foundInClass / inClass;
	}
	
}
