import java.util.ArrayList;


public class RandomDecisionTree {

	RandomDecisionTree(ArrayList<Dataset> training, int smallN ){
		int bigN = training.size();
		int dim  = training.get(0).dim;
		
		// baue eine zufällige Menbe mit zurücklegen
		ArrayList<Dataset> buildSet = new ArrayList<Dataset>();
		for(int i=0; i < bigN; i++){
			buildSet.add( training.get( (int) (Math.random()*bigN) ) ); // ziehen mit zurücklegen
		}
		
		// Wähle zufällig smallN Attribute
		int attributSet[] = new int[smallN];
		for(int i=0; i < smallN; i++){
			attributSet[i] = (int) Math.random()*dim;  //  
		}
		
		// hier kommt der eigentliche Aufbau des Baumes
	}
	
	private double getEntropy(int attribut, ArrayList<Dataset> buildSet){
		return 0.0;
	}
	private double getGain(int attribut, ArrayList<Dataset> buildSet){
		return 0.0;
	}
}
