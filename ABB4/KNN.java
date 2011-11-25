import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class KNN {

	public Database db;
	
	
	public KNN(Database db){
		this.db = db;
	}
	
	
	
	// Returns the most appearing class of k neighbors
	public int classifyWithKNN(Dataset d, int k){
		ArrayList<Dataset> neighbors = getKNN(d, k);
		return getMostAppearing(neighbors);
	}
	
	
	
	// Returns the k nearest neighbor-datasets of the database
	private ArrayList<Dataset> getKNN(Dataset d, int k){
		// create list of neighbours
		ArrayList<Dataset> neighbors = new ArrayList<Dataset>();
		for(Relation r : db.relations){
			if(r.dataset != d){
				// Check whether there is still enough space in member list
				if (neighbors.size() < k){
					neighbors.add(r.dataset);
				}else{
					// check if relations distance is smaller than currently found member's distance
					double tempDist = d.getDistance(r.dataset);
					for(Dataset n : neighbors){
						// if we found a neighbor whom's distance is larger we replace it
						if (tempDist < n.getDistance(r.dataset)){
							neighbors.remove(n);
							neighbors.add(d);
							break;
						}
					}
				}
			}
		}
		return neighbors;
	}
	
	
	
	// Returns the class which is the most appearing one of the neighbors' classes
	private int getMostAppearing(ArrayList<Dataset> neighbors){
		// Find out most appearing member class
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(Dataset d : neighbors){
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
		
		return bestKey;
	}
	
	
	
}
