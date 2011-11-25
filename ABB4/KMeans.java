import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/*
 * K-Means is an instance of the expectation maximization algorithm concept.
 * The idea is to classify each Dataset to be member of the prototype that is 
 * closest in terms of euklidean distance (expectation)
 * and to reposition each prototype to the center point of his cluster 
 * afterwards (maximization). 
 */
public class KMeans extends AbstractEM {
	public int rounds;
	
	KMeans(Database db, int k) throws Exception{
		this.db = db;
		this.dimensions = db.dimensions;
		this.trainSize = db.datasets.size();
		
		db.addAll( generatePrototypes( k ));
	}

	
	
	// Calculates for each Prototype the euklidean distance to each prototype 
	void expectation() {
		for( Relation r: db.relations){
			r.distance = LinearAlgebra.distanceEuklid(r.dataset, r.prototype);
		}
	}


	
	// Identifies the member of each prototype's cluster and repositions them
	// to the cluster's center.
	void maximization() {
		identifyPrototypes();
		
		for(Prototype p: db.prototypes){
			// Get members, don't use Bayes
			ArrayList<Dataset> members = p.getMembers(false);
			
			// test whether prototype has no associated members 
			if(members.size() == 0){
				p.reinitialize();
			}else{
				p.features = LinearAlgebra.getMyu(members);
			}
		}
		
	}

	
	
	// breaking condition is a number of rounds
	boolean condition() {
		rounds++;
		return 20 > rounds;
	}


	
	// identifies each prototype's class by counting most appearing member class
	public void identifyPrototypes() {
		for (Prototype p : db.prototypes) {
			// Get members, don't use Bayes
			ArrayList<Dataset> members = p.getNearestMembers();
			// Find out most appearing member class
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			for(Dataset d : members){
				if (hm.containsKey(d.correctKlass)) {
					Integer tmp = hm.get(d.correctKlass);
					hm.put(d.correctKlass, tmp + 1);
				} else {
					hm.put(d.correctKlass, 1);
				}
			}

			// get most appearing out of hashmap
			Set<Integer> keys = hm.keySet();
			int max = 0;
			int bestKey = 0;
			for (Integer k1 : keys) {
				int value = hm.get(k1);
				if (max < value) {
					max = value;
					bestKey = k1;
				}
			}

			// Now the prototype knows the class that is represented by most of his members
			p.correctKlass = bestKey;
		}
	}
}
