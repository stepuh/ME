import java.util.ArrayList;


public abstract class AbstractEM {
	Database db;
	int dimensions;
	int trainSize;
	
	public ArrayList<Prototype> generatePrototypes(int n){
		ArrayList<Prototype> prototypes = new ArrayList<Prototype>();
		for(int i=0; i<n; i++){
			double[] features = new double[dimensions];
			for(int j=0; j<dimensions; j++){
				features[j] = Math.random() *100;
			}
			prototypes.add( new Prototype(features) );
		}
		return prototypes;
	}
	
	public void reInitializePrototype( Dataset p ){
		p.features = generatePrototypes(1).get(0).features;
	}
	
	private void runRound(){
		expectation();
		maximization();
	}
	
	public void run(){
		// till terminate
		while( condition()){
			runRound();
		}
	}
	abstract void expectation();
	abstract void maximization();
	abstract boolean condition();
	abstract void identifyPrototypes();
}
