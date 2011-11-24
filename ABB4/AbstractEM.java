	import java.util.ArrayList;


/*
 *	Expectation Maximization is a set of Algorithms that consist of 
 *	two steps: Expectation and Maximization.
 *	It divides a set of train data into k clusters.
 *	Explicit instances of this concept are the Gaussian-Mixture-Model-EM
 *	and the K-Means algorithm.
 */
public abstract class AbstractEM {
	Database db;		// set of train data
	int dimensions;		// dimensions of each vector in train data
	int trainSize;		// number of vectors in database 
	
	
	
	
	// Generates k prototypes and positions them randomly
	public ArrayList<Prototype> generatePrototypes(int n){
		ArrayList<Prototype> prototypes = new ArrayList<Prototype>();
		for(int i=0; i<n; i++){
			double[] features = new double[dimensions];
			for(int j=0; j<dimensions; j++){
				features[j] = Math.random() *100;
			}
			prototypes.add( new Prototype(features) );
		}
		return prototypes;
	}
	
	
	
	// Runs one round of expectation maximization
	private void runRound(){
		expectation();
		maximization();
	}
	
	
	
	// The expectation maximization algorithm runs as long as a break condition resolves true
	public void run(){
		// till terminate
		while( condition() ){
			runRound();
		}
	}
	
	
	
	// The expectation step calculates each Dataset's probability 
	// to be member of a certain cluster
	abstract void expectation();
	
	// The maximization step modifies the prototype so next round 
	// it represents it's cluster more sufficiently
	abstract void maximization();
	
	// The expectation maximization algorithms runs as long as
	// this function resolves to true
	abstract boolean condition();
	
	// Each cluster hopefully contains a majority of one class.
	// This function calculates this majority for each prototype.
	abstract void identifyPrototypes();
}
