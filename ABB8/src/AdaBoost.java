import java.util.ArrayList;


public class AdaBoost {

	ArrayList<Classifier> classifiers;
	
	public AdaBoost(ArrayList<Classifier> classifiers, int iterationNum){
		this.classifiers = classifiers;
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
