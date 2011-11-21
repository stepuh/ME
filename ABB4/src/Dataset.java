import java.util.ArrayList;

public class Dataset {
	
	int calculatedKlass;
	int correctKlass;
	double[] features;						
	ArrayList<Relation> relations;
	
	public Dataset( double [] f){
		features = f;
		relations = new ArrayList<Relation>();
	}
	
	public Dataset( int k, double[] f){
		correctKlass = k;
		features = f;
		relations = new ArrayList<Relation>();
	}
	
	public ArrayList<Dataset> getRelatedDatasets(){
		ArrayList<Dataset> result = new ArrayList<Dataset>();
		for(Relation r: relations){
			if( this.equals( r.dataset )  )
				result.add(r.prototype);
			else
				result.add(r.dataset);
		}
		return result;
	}
	
	


	
	
}
