import java.util.ArrayList;


public class AdaBoost {

	ArrayList<Classifier> classifiers;
	ArrayList<Double> weights;
	
	public AdaBoost(ArrayList<Classifier> classifiers, int iterationNum){
		this.classifiers = classifiers;
		for(int i=0; i < iterationNum; i++ ){
			iterate();
		}
		
		this.weights = new ArrayList<Double>();
		for(int i=0; i<classifiers.size(); i++){
			weights.add((Double)(1/(double)classifiers.size()));
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
