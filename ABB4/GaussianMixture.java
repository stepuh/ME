import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class GaussianMixture extends AbstractEM{
	public int rounds;
	
	// Calculates for all Datasets the expectation for each prototype to be a member it's cluster
	void expectation() {
		for( Relation r: db.relations){
			LinearAlgebra.calculateExpectation(r);
		}
	}

	
	
	// Calculates new a-priori probability pi, new myi (features) and new kovarianz matrix, after 
	// which the class is identified a prototype's cluster is representing
	void maximization() {
		for( Prototype p : db.prototypes){
			p.calcPi();
			p.calcMyu();
			p.calcS();
		}
		identifyPrototypes();
	}

	
	
	// breaking condition is a number of rounds
	boolean condition() {
		rounds++;
		return 20 > rounds;
	}


	
	// identifies each prototype's class by counting most appearing member class
	public void identifyPrototypes() {
		for (Prototype p : db.prototypes) {
			// Get Members, with bayes
			ArrayList<Dataset> members = p.getMembers(true);

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
