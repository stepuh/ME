import java.util.ArrayList;


public class AdaBoost {

	ArrayList<Classifier> classifiers;
	ArrayList<Pattern> patterns; //
	
	public AdaBoost(ArrayList<Classifier> classifiers, int iterationNum, ArrayList<Pattern> patterns){
		this.classifiers = classifiers;
		this.patterns = patterns;

		// gewichte initialisieren
		for(Pattern p: this.patterns){
			p.weight = 1.0 / patterns.size();
		}
		
		for(int i=0; i < iterationNum; i++ ){
			iterate();
		}
		
	}
	
	public void selectBestClassifier(){
		
		
	}
	
	
	public void selectAlphaM(){
		
	}
	
	
	public void updateWeights(){
				
	}
	
	
	public void iterate(){
		selectBestClassifier();
		selectAlphaM();
		updateWeights();
	}
	
}
