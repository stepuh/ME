

import java.io.FileNotFoundException;
import java.util.ArrayList;



public class Client20 {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		ArrayList<Dataset> training = new Reader("digits-testing-neu.txt").getDatasets();
		int threadsN = 1000;
		int smallN = 10;
		
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
			AddTreeThread t = new AddTreeThread(forest, training, smallN);
			t.start();
			threads.add(t);
		}
		
		// block till every tree is fully grown :)
		for(AddTreeThread t: threads){
			t.join(); 
		}
		
		System.out.println("Forest size: "+forest.size());



	}

}
