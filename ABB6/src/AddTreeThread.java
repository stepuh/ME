package src;

import java.util.ArrayList;


// Der Thread bekommt den Random Forest und erstellt einen RDT und f�gt ihn hinzu. Und stribt.
public class AddTreeThread extends Thread{

	private ArrayList<RandomDecisionTree> forest;
	private ArrayList<Dataset> training;
	int smallN;
	
	// Constructor
	AddTreeThread( ArrayList<RandomDecisionTree> forest, ArrayList<Dataset> training, int smallN){
		this.forest = forest;
		this.training = training;
		this.smallN = smallN;
	}
	
	// Wird ausgef�hrt wenn auf einem Thread start() aufgerufen wird
	public void run(){
		forest.add(new RandomDecisionTree(training, smallN ));
	}
}
