import Jama.Matrix;



public class Fischer {
	Database db;
	
	
	Fischer( Database db){
		this.db = db;
	}
	
	public double[] getW(Prototype protoA, Prototype protoB){
		Matrix kovarA = protoA.s;
		kovarA.print(16, 4);
		Matrix kovarB = protoB.s;
		kovarB.print(16, 4);
		Matrix myuA = new Matrix( protoA.features, 1).transpose();
		Matrix myuB = new Matrix( protoB.features, 1).transpose();
		
		Matrix sum = kovarA.plus(kovarB).inverse();
		Matrix diff = myuA.minus(myuB);
		
		double[][] result= sum.times(diff).getArray();
		
		return result[0];

		
	}
	

}
