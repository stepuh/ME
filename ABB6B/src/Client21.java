

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;



public class Client21 {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		ArrayList<Dataset> training = new Reader("digits-testing-neu.txt").getDatasets();
		
		for(int baumanzahl=10; baumanzahl<1000; baumanzahl+= 10){
			// do preprocessing: remove Datasets with class -1
			ArrayList<Dataset> pollutedDatasets = new ArrayList<Dataset>();
			for(Dataset d: training){
				if( -1 == d.correctKlass ){
					pollutedDatasets.add(d);
				}
			}
			training.removeAll(pollutedDatasets);
			
			double gemitteltesErgebnis = 0;
			
			for(int j=0; j<10;j++){
				
				ArrayList<RandomDecisionTree> forest = new ArrayList<RandomDecisionTree>();
				for(int z=0; z<baumanzahl; z++){
					forest.add( new RandomDecisionTree(training));
				}
				
				int korrekt = 0;
				HashMap<Integer, Integer> valueMap = new HashMap<Integer,Integer>();

				for(Dataset d : training){
					valueMap.clear();
					int richtig = d.correctKlass;

					
					// Teste alle Bäume durch
					for(RandomDecisionTree tree : forest){
						if ( !tree.s.contains(d)){ // out of bag method
							int erkannt = tree.traverse(d);
							if (valueMap.containsKey(erkannt)) {
								Integer tmp = valueMap.get(erkannt);
								valueMap.put(erkannt, tmp + 1);
							} else {
								valueMap.put(erkannt, 1);
							}
						}
					}
						
					Set<Integer> keys = valueMap.keySet();
					int max = 0;
					int bestKey = -1;
					for (Integer k1 : keys) {
						if(k1 == -1){
							continue;	// don't count misses
						}
						int value = valueMap.get(k1);
						if (max < value) {
							max = value;
							bestKey = k1;
						}
					}
					int klass = bestKey;
					if (klass == richtig ){
						korrekt++;
					}
				}

				double pr = (double) korrekt / (double) training.size();
				gemitteltesErgebnis += pr;
				
			}
			gemitteltesErgebnis /= 10;
			System.out.println(+baumanzahl+ " "+gemitteltesErgebnis);

		}
	}

}
