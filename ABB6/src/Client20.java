import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Client20 {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		ArrayList<Dataset> training = new Reader("digits-testing-neu.txt").getDatasets();
		int threadsN = 100;
		int smallN = 10;
		
		ArrayList<RandomDecisionTree> forest = new ArrayList<RandomDecisionTree>();
		
		// Add the trees concurrently!
		ArrayList<AddTreeThread> threads = new ArrayList<AddTreeThread>();
		for(int i=0; i<threadsN; i++){
			AddTreeThread t = new AddTreeThread(forest, training, smallN);
			t.start();
			threads.add(t);
		}
		for(AddTreeThread t: threads){
			t.join(); // block till every tree is fully grown :)
		}
		
		System.out.println(forest.size());



	}

}
