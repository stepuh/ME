import java.util.ArrayList;

import Jama.Matrix;


public class LinearAlgebra {
	
	
	// calculate euklidian distance between a and b
	public static double distanceEuklid(Container a, Container b){
		int result = 0;
		for(int i=0; i< a.features.length; i++){
    		result += Math.pow(a.features[i] - b.features[i], 2);
    	}
    	return Math.sqrt(result);
	}
	
	public static double[] getMyu(ArrayList<Dataset> datasets){
		int d = datasets.get(0).features.length;
		double[] myu = new double[d];
		for(int i=0; i<d; i++)
			myu[i]=0;
		// sum over all datasets and their features
		for(Dataset data: datasets){
			for(int i=0; i<d; i++)
				myu[i] += data.features[i];
		}
		// normalize
		for(int i=0; i<d; i++)
			myu[i] /= datasets.size();

		return myu;
	}
	
	
	
	
	
	
	// uses the bayes-classificator to calculate the probability that
	// a dataset is indeed member of a certain class if the expectation is known 
	//
	// Bayes says:
	// P(A,B) = P(A)*P(B|A) = P(B)*P(A|B) <- always true
	// => P(B|A) = P(B) * P(A|B) / P(A)
	//
	// We use that and calculate the following:
	//
	// P(xj|Ci):expectation that Dataset xj is member of Prototype Ci
	// P(Ci):	a priori probability for Prototype Ci
	// P(xj):	probability for xj to be a member of any Prototype
	public static double getBayes(Relation r){
		double P_xj_Ci = 0.0;
		double P_Ci = 0.0;
		double P_xj = 0.0;
		
		P_xj_Ci = r.probability;
		P_Ci = r.prototype.pi;
		P_xj += r.probability * P_Ci;

		return P_xj_Ci * P_Ci / P_xj; 
	}

	
	

	
	
	
	// Calculates the probability that <from> is a member of <prototype> with Gauss
	public static void calculateExpectation(Relation r){
		// init
		double[] myu = LinearAlgebra.getMyu( r.prototype.getRelated() );
		Matrix s = r.prototype.s;
		
		double expectation = 0.0;
		expectation += r.prototype.pi; 
		System.out.println("pi:" + expectation);
		
		// find out dimensions
		int dim = r.prototype.dim;

		// left part of formula
		double firstLeft = Math.pow(2.0 * Math.PI, dim * -0.5);
		expectation = expectation * firstLeft;
		
		double firstRight =  Math.pow( s.det(), -0.5);
		expectation = expectation * firstRight;
		System.out.print("calc: "+expectation);
		
		// right part of formula: e^(...)
		double[] tempVektor = new double[dim];
		
		for(int i=0; i<dim; i++){
			tempVektor[i] = r.dataset.features[i] - myu[i];
		}
		
		Matrix v = new Matrix(tempVektor,1);
		Matrix vT = v.transpose();
		
		Matrix resultMatrix =  v.times(s.inverse());
		resultMatrix = resultMatrix.times(vT);
		resultMatrix = resultMatrix.times(-0.5);
		
		double powValue = resultMatrix.get(0, 0);
		System.out.println("* e^:"+powValue);
		
		expectation *= Math.exp(powValue); 
		System.out.println("final expectaton:"+expectation);
		r.probability = expectation;
	}
}
