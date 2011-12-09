

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;



public class Client20 {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		ArrayList<Dataset> training = new Reader("digits-testing-neu.txt").getDatasets();
		
		// do preprocessing: remove Datasets with class -1
		ArrayList<Dataset> pollutedDatasets = new ArrayList<Dataset>();
		for(Dataset d: training){
			if( -1 == d.correctKlass ){
				pollutedDatasets.add(d);
			}
		}
		training.removeAll(pollutedDatasets);
		
		
		
		ArrayList<RandomDecisionTree> forest = new ArrayList<RandomDecisionTree>();
		Random rnd = new Random();
		for(int i=0; i<10; i++){
			forest.add(new RandomDecisionTree(training, rnd));
		}
		
	
		
		System.out.println("Forest size: "+forest.size());



	}

}
