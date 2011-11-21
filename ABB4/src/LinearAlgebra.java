import java.util.ArrayList;

import Jama.Matrix;


public class LinearAlgebra {
	
	
	// calculate euklidian distance between a and b
	public static double distanceEuklid(Dataset a, Dataset b){
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
	
	
	public static double[] getMyu(Dataset prototype){
		ArrayList<Dataset> datasets = prototype.getRelatedDatasets();
		return getMyu(datasets);
	}
	
	
	
	public static double getPi( Dataset prototype){
		double result = 0;
		int size = prototype.relations.size();
		for(Relation r: prototype.relations)
			result += r.probability;
		result /= size;
		return result;
	}
	
	
	
	public static double[][] getS( Dataset prototype){
		
		// init
		int d = prototype.features.length;
		ArrayList<Relation> relations = prototype.relations;
		Matrix s = new Matrix(d, d);
		
		double[] myu = getMyu(prototype);
		
		// sum over relations to calculate S
		double sum = 0;
		for(Relation r: relations){
			sum += r.probability;
			
			double[] dist = new double[d];			
			for(int i=0; i<d; i++){
				dist[i] = r.dataset.features[i] - myu[i];
			}
			
			Matrix distMatrix = new Matrix(dist,1);
			Matrix distMatrixT = distMatrix.transpose();
			s = s.plus(distMatrixT.times(distMatrix));
			s = s.times(r.probability);
		}	
		
		s = s.times(1.0/sum);
		return s.getArray();
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
		P_Ci = getPi(r.prototype);
		P_xj += r.probability * P_Ci;

		return P_xj_Ci * P_Ci / P_xj; 
	}
	
	
	
	public static Dataset getMostProbable(Dataset d){
		Relation mostProbable = d.relations.get(0);
		for(Relation r: d.relations){
			if( r.probability > mostProbable.probability){
				mostProbable = r;
			}
		}
		return mostProbable.prototype;
	}

	
	
	public static Dataset getMostProbableBayes(Dataset d){
		Relation mostProbable = d.relations.get(0);
		for(Relation r: d.relations){
			if( getBayes(r) > getBayes(mostProbable)){
				mostProbable = r;
			}
		}
		return mostProbable.prototype;
	}
}
