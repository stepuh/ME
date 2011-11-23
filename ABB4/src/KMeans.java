import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class KMeans extends AbstractEM {
	int rounds;
	
	KMeans(Database db, int k) throws Exception{
		this.db = db;
		this.dimensions = db.dimensions;
		this.trainSize = db.datasets.size();
		
		db.addAll( generatePrototypes( k ));

	}

	void expectation() {
		for( Relation r: db.relations){
			// probability = distance
			r.distance = LinearAlgebra.distanceEuklid(r.dataset, r.prototype);
			// r.dataset.calculatedKlass = LinearAlgebra.getMostProbable(r.dataset).c
		}
	}

	void maximization() {
		identifyPrototypes();
		
		for(Prototype p: db.prototypes){
			ArrayList<Dataset> members = new ArrayList<Dataset>();
			

//			for(Dataset d: db.datasets){
//				if( LinearAlgebra.getMostProbable(d) == p){
//					members.add(d);
//				}
//			}
//			
//			// test whether prototype has no associated members (bad luck!)
//			if( 0 == members.size()){
//				reInitializePrototype(p);
//			}else{
//				p.features = LinearAlgebra.getMyu(members);
//			}
		}
		
	}

	boolean condition() {
		rounds++;
		return 20 > rounds;
	}


	// identifies each prototype's class by counting most appearing member class
	public void identifyPrototypes() {
		for (Prototype p : db.prototypes) {
			// Create List of members
//			ArrayList<Dataset> memberList = new ArrayList<Dataset>();
//			for (Dataset d : db.datasets) {
//				if( LinearAlgebra.getMostProbable(d) == p){
//					memberList.add(d);
//				}
//			}
//
//			// Find out most appearing member class
//			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
//			for(Dataset d : memberList){
//				if (hm.containsKey(d.correctKlass)) {
//					Integer tmp = hm.get(d.correctKlass);
//					hm.put(d.correctKlass, tmp + 1);
//				} else {
//					hm.put(d.correctKlass, 1);
//				}
//			}
//
//			// get most appearing out of hashmap
//			Set<Integer> keys = hm.keySet();
//			int max = 0;
//			int bestKey = 0;
//			for (Integer k1 : keys) {
//				int value = hm.get(k1);
//				if (max < value) {
//					max = value;
//					bestKey = k1;
//				}
//			}
//
//			// Now the prototype knows the class that is represented by most of his members
//			p.correctKlass = bestKey;
		}
	}
}
