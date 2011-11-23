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
		System.out.println("Matrix A:");
		Matrix kovarA = protoA.s;
		kovarA.print(16, 4);
		System.out.println("Matrix B:");
		Matrix kovarB = protoB.s;
		kovarB.print(16, 4);
		
		Matrix myuA = new Matrix( protoA.features, 1);
		System.out.println("myu A:");
		myuA.print(16, 4);
		
		Matrix myuB = new Matrix( protoB.features, 1);
		System.out.println("myu B:");
		myuB.print(16,4);
		
		Matrix sum = kovarA.plus(kovarB).inverse();
		System.out.println("(A+B)^-1:");
		sum.print(16,4);
		
		Matrix diff = myuA.minus(myuB).transpose();
		System.out.println("myuA - myuB:");
		diff.print(16, 4);
		
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
