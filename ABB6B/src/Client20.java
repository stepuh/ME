

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;



public class Client20 {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		ArrayList<Dataset> training = new Reader("digits-testing-neu.txt").getDatasets();
		int threadsN = 100;
		
		// do preprocessing: remove Datasets with class -1
		ArrayList<Dataset> pollutedDatasets = new ArrayList<Dataset>();
		for(Dataset d: training){
			if( -1 == d.correctKlass ){
				pollutedDatasets.add(d);
			}
		}
		training.removeAll(pollutedDatasets);
		
		
		
		ArrayList<RandomDecisionTree> forest = new ArrayList<RandomDecisionTree>();

		
		// Add the trees concurrently!
		ArrayList<AddTreeThread> threads = new ArrayList<AddTreeThread>();
		for(int i=0; i<threadsN; i++){
			AddTreeThread t = new AddTreeThread(forest, training);
			t.start();
			threads.add(t);
		}
		
		// block till every tree is fully grown :)
		for(AddTreeThread t: threads){
			t.join(); 
		}
		
		System.out.println("Forest size: "+forest.size());

		
		int korrekt = 0;
		HashMap<Integer, Integer> valueMap = new HashMap<Integer,Integer>();
		for(Dataset d : training){
			valueMap.clear();
			int richtig = d.correctKlass;
			
			// Teste alle Bäume durch
			for(RandomDecisionTree tree : forest){
				int erkannt = tree.traverse(d);

				if (valueMap.containsKey(erkannt)) {
					Integer tmp = valueMap.get(erkannt);
					valueMap.put(erkannt, tmp + 1);
				} else {
					valueMap.put(erkannt, 1);
				}
			}

			Set<Integer> keys = valueMap.keySet();
			int max = 0;
			int bestKey = 0;
			for (Integer k1 : keys) {
				int value = valueMap.get(k1);
				if (max < value) {
					max = value;
					bestKey = k1;
				}
			}
			int klass = bestKey;//valueMap.get(bestKey);
			
			if (klass == richtig){
				korrekt++;
			}
		}

		double pr = (double) korrekt / (double) training.size();
		System.out.println("richtig erkannt: " + pr);
	}

}
