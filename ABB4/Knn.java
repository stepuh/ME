import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class Knn extends AbstractEM{
	Database db;
	public int rounds;
	
	
	Knn(Database db, int k) throws Exception{
		this.db = db;
		db.addAll( generatePrototypes(k) );
		rounds = 0;
	}
	

	// expectation = distance
	void expectation() {
		for( Relation r: db.relations){
			r.distance = LinearAlgebra.distanceEuklid(r.dataset, r.prototype);
			System.out.println(r.distance);
			System.out.println("TEST");
		}		
		
	}

	void maximization() {
		identifyPrototypes();
		
		for(Prototype p: db.prototypes){
			// Get members, don't use Bayes
			ArrayList<Dataset> members = p.getNearestMembers();
			
			// test whether prototype has no associated members 
			if(members.size() == 0){
				p.reinitialize();
			}else{
				p.features = LinearAlgebra.getMyu(members);
			}
		}
	}

	boolean condition() {
		rounds++;
		System.out.println(rounds);
		return rounds < 10;
	}


	void identifyPrototypes() {
		for (Prototype p : db.prototypes) {
			// Get members, use Euklid
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
