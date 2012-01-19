import java.util.ArrayList;


public class AdaBoost {

	ArrayList<Classifier> classifiers;
	ArrayList<Pattern> patterns; //
	ArrayList<Classifier> committee;
	
	double weTmp;
	
	public AdaBoost(ArrayList<Classifier> classifiers, int iterationNum, ArrayList<Pattern> patterns){
		this.classifiers = classifiers;
		this.patterns = patterns;
		this.committee = new ArrayList<Classifier>();

		// gewichte initialisieren
		for(Pattern p: this.patterns){
			p.weight = 1.0 / patterns.size();
		}
		

		
	}
	
	public int classify( Pattern p){
		double tmpResult = 0;
		for(Classifier c: this.committee){
			tmpResult += c.weight * c.classify(p);
		}
		if( tmpResult > 0){
			return 1;
		}else{
			return -1;
		}
	}
	
	public Classifier selectBestClassifier(){
		Classifier bestClassifier = null;
		weTmp=1.;
		
		for(Classifier c: classifiers){
			double tmpResult=0;
			//sum
			for(int i=0; i<patterns.size(); i++){
				Pattern p = patterns.get(i);
				if(c.results.get(i) != p.teaching){
					tmpResult += p.weight;
				}
			}
			// minimize
			if(tmpResult < weTmp){
				weTmp = tmpResult;
				bestClassifier = c;
			}
		}
		return bestClassifier;
	}
	
	
	public void selectAlphaM(Classifier c){
		c.weight = .5*Math.log((1-weTmp)/weTmp);
	}
	
	
	public void updateWeights(Classifier c){
		for(int i=0; i<patterns.size(); i++){
			Pattern p = patterns.get(i);
			if(c.results.get(i) != p.teaching){
				p.weight *= Math.exp(c.weight);
			}else{
				p.weight *= Math.exp(-c.weight);
			}
		}
	}
	
	public void iterate(){
		Classifier c = selectBestClassifier();
		selectAlphaM( c );
		updateWeights( c );
		this.committee.add(c);
	}
	
}
