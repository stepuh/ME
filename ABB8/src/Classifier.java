import java.util.ArrayList;


public class Classifier {

	public PerzeptronNetz perzeptron;		// perzeptron network used to classify
	public int corrects;					// number of correct identifies datasets

	
	public Classifier(ArrayList<Dataset> datasets){
		// Create random neuronal net for classification 
		PerzeptronNetz perzeptron = new PerzeptronNetz(2, 1);
		
		// Test perzeptron on all Datasets
		corrects = 0;
		for(Dataset d : datasets){
			double[] correctClass = new double[1];
			correctClass[0] = d.correctKlass;
			Muster m = new Muster(d.features, correctClass);
			double[] ergebnis = perzeptron.ergebnisZuMuster(m);
			
			if (ergebnis[0] == correctClass[0]){
				corrects++;
			}
		}		
	}
	
}
