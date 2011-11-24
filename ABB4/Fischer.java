import java.util.Arrays;

import Jama.Matrix;



public class Fischer {
	Database db;
	
	
	Fischer( Database db){
		this.db = db;
	}
	

	
	// Returns vector w so that the projected clusters of Prototype A and Prototype B
	// on w are well parted and have minimal kovarianz
	public double[] getW(Prototype protoA, Prototype protoB){
		Matrix kovarA = protoA.s;
		Matrix kovarB = protoB.s;

		
		Matrix myuA = new Matrix( protoA.features, 1);
		
		Matrix myuB = new Matrix( protoB.features, 1);
		
		Matrix sum = kovarA.plus(kovarB).inverse();
		
		Matrix diff = myuA.minus(myuB).transpose();
		
		// get 2-dim array as temporarily result
		double[][] tmpResult= sum.times(diff).getArray();
		
		// convert into 1 dimensional array as final result
		double[] oneDimResult = new double[protoA.dim];
		for(int i = 0; i < protoA.dim; i++){
			oneDimResult[i] = tmpResult[i][0];
		}
		System.out.println("result vector: " + Arrays.toString(oneDimResult));
		
		return oneDimResult;
	}
	

}
