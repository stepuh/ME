import java.util.ArrayList;

public class Dataset extends Container{
	
	public Dataset(double[] f) {
		super(f);
	}
	
	public Dataset(int c, double[] f) {
		super(c, f);
	}
	
	
	public final ArrayList<Prototype> getRelated(){
		ArrayList<Prototype> result = new ArrayList<Prototype>();
		for(Relation r: relations){
			result.add(r.prototype);
		}
		return result;
	}
	
	
	
	public Prototype getMostProbable(){
		Relation mostProbable = relations.get(0);
		for(Relation r: relations){
			if( r.probability > mostProbable.probability){
				mostProbable = r;
			}
		}
		return mostProbable.prototype;
	}
	
	
	
	public Prototype getMostProbableBayes(Dataset d){
		Relation mostProbable = d.relations.get(0);
		for(Relation r: d.relations){
			if( LinearAlgebra.getBayes(r) > LinearAlgebra.getBayes(mostProbable)){
				mostProbable = r;
			}
		}
		return mostProbable.prototype;
	}
}
