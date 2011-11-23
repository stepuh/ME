import java.util.Arrays;

import Jama.Matrix;



public class Fischer {
	Database db;
	
	
	Fischer( Database db){
		this.db = db;
	}
	
	
	public void initProtos(){
		initializePrototypesPi();
		for(Prototype p : db.prototypes){
			p.features = LinearAlgebra.getMyu(db.datasets);
			p.calcS();
		}
	}
	
	
	
	

	// initializes every prototype's a-priori probability
	public void initializePrototypesPi(){
		// calculate sum over all datasets probabilities
		double sumD_Pr = 0.0;
		for(Dataset d : db.datasets){
			sumD_Pr += d.sumProbabilitys();
		}
		
		// set every prototype's a-priori probability to be
		// the the sum over all related probabilities divided by the sum 
		// over all probabilities
		for(Prototype p : db.prototypes){
			double sumP_Pr = p.sumProbabilitys();
			sumP_Pr = sumP_Pr / sumD_Pr;
		}
	}
	
	
	
	// Returns vector w so that the projected clusters of Prototype A and Prototype B
	// on w are well parted and have minimal kovarianz
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
		
		System.out.println("result vector: " + Arrays.toString(result[0]));
			
		return result[0];
	}
	

}
