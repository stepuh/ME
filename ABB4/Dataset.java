import java.util.ArrayList;


/*
 * A Dataset is an instance of the train data.
 * As a vector it represents a correctClass in a space of <dim> dimensions.
 */
public class Dataset extends Container{
	
	public Dataset(double[] f) {
		super(f);
	}
	
	public Dataset(int c, double[] f) {
		super(c, f);
	}
	
	
	
	// Returns a list of all related Prototypes
	public final ArrayList<Prototype> getRelated(){
		ArrayList<Prototype> result = new ArrayList<Prototype>();
		for(Relation r: relations){
			result.add(r.prototype);
		}
		return result;
	}
	
	
	
	// Returns the Prototype that is most probably the representative of 
	// the cluster this dataset is a member of. In terms of euklidean distance.
	// This is needed for K-Means Algorithm
	public Prototype getMostProbable(){
		Relation mostProbable = relations.get(0);
		for(Relation r: relations){
			if( r.probability > mostProbable.probability){
				mostProbable = r;
			}
		}
		return mostProbable.prototype;
	}
	
	
	
	// Returns the Prototype that is most probably the representative of 
	// the cluster this dataset is a member of. In terms of Bayes Classificator.
	// This is neeeded for Gaussian Mixture Model Expectation Maximization.
	public Prototype getMostProbableBayes(){
		Relation mostProbable = relations.get(0);
		for(Relation r: relations){
			if( LinearAlgebra.getBayes(r) > LinearAlgebra.getBayes(mostProbable)){
				mostProbable = r;
			}
		}
		return mostProbable.prototype;
	}
	
	public Prototype getNearestEuklid(){
		Relation nearest = relations.get(0);
		for(Relation r: relations){
			if( nearest.distance > r.distance){
				nearest = r;
			}
		}
		return nearest.prototype;
		
	}
}
