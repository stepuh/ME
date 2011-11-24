import java.util.ArrayList;

import Jama.Matrix;


/*
 * A Prototype is a representative of a certain cluster.
 * For Gaussian Mixture Model it also represents the cluster's Gaussian Variance
 * and a-priori probability.
 */
public class Prototype extends Container{

	double pi;		// a-priori probability
	Matrix s;		// kovarianz matrix
	
	public Prototype(double[] f) {
		super(f);
	}
	
	public Prototype(int c, double[] f) {
		super(c, f);
	}
	
	
	
	// Returns a list of Datasets that are related with this prototype
	public ArrayList<Dataset> getRelated(){
		ArrayList<Dataset> result = new ArrayList<Dataset>();
		for(Relation r: relations){
			result.add(r.dataset);
		}
		return result;
	}
	
	
	
	// Calculates the a-priori probability
	public void calcPi(){
		pi = 0;
		int size = relations.size();
		for(Relation r: relations)
			pi += r.probability;
		pi /= size;
	}
	
	
	
	// Calculates the center point
	public void calcMyu(){
		// initialize 0-vector
		double[] myu = new double[features.length];
		for(int i = 0; i < features.length; i++){
			myu[i] = 0.0;
		}
		
		// sum over all related Datasets and their expectation	
		double sumExpectation = 0.0;
		for(Relation r : relations){
			sumExpectation += r.probability;
			for(int i = 0; i < features.length; i++){
				myu[i] += r.dataset.features[i] * r.probability;
			}
		}
		
		// normalize
		for(int i = 0; i < features.length; i++){
			myu[i] /= sumExpectation;
		}
		
		features = myu;
	}
	
	
	
	// Calculates the kovarianz matrix 
	public void calcS(){
		s = new Matrix(dim, dim);
		
		double[] myu = LinearAlgebra.getMyu( this.getRelated() );
		
		// sum over relations to calculate S
		double sum = 0;
		for(Relation r: relations){
			sum += r.probability;
			
			double[] dist = new double[dim];			
			for(int i=0; i<dim; i++){
				dist[i] = r.dataset.features[i] - myu[i];
			}
			
			Matrix distMatrix = new Matrix(dist,1);
			Matrix distMatrixT = distMatrix.transpose();
			s = s.plus(distMatrixT.times(distMatrix));
			s = s.times(r.probability);
		}	
		
		s = s.times(1.0/sum);
	}

	
	
	// Repositions the prototype randomly
	public void reinitialize(){
		for(int i=0; i<features.length; i++){
			features[i] = Math.random() *100;
		}
	}
	
	
}
