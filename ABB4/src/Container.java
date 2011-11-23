import java.util.ArrayList;


/*
 * A Container actually just a point in space with <dim> dimensions.
 * It can be either a prototype or a dataset.
 * It also has a absolute value correctKlass and a temporarily value
 * calculated class that is valid in only one specific round of
 * the expectation maximization algorithms.
 */
public abstract class Container {
	
	ArrayList<Relation> relations;	// list of relations to either all it's related prototypes or it's datasets
	int calculatedKlass;			// temporarily value, classified every expectation maximization round
	int correctKlass;				// prototype: class of most members; dataset: represented class 
	double[] features;				// position vector
	int dim;						// dimensions of vector
	
	
	
	public Container( double [] f){
		features = f;
		dim = features.length;
		relations = new ArrayList<Relation>();

	}
	
	public Container( int k, double[] f){
		this(f);
		correctKlass = k;
	}

	
	
	// Returns the euklidean distance to another container
	public double getDistance(Container c){
		return LinearAlgebra.distanceEuklid(this, c);
	}
	

	
	// Prototype: Returns the sum over all related Datasets' probabilities to be
	// a member of the prototype's cluster
	// Dataset: Returns the sum over all related Prototypes' probabilities to be
	// a member of the prototype's cluster
	public double sumProbabilitys(){
		double pr = 0.0;
		for(Relation r : relations){
			pr += r.probability;
		}
		return pr;
	}
	
	
	// Returns a list of all related Containers.
	// A Dataset is related to Prototypes and
	// a prototype is related to Datasets
	abstract ArrayList<? extends Container> getRelated();

}
