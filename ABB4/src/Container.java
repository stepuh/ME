import java.util.ArrayList;

public abstract class Container {
	
	ArrayList<Relation> relations;
	int calculatedKlass;
	int correctKlass;
	double[] features;
	int dim;
	
	
	
	public Container( double [] f){
		features = f;
		dim = features.length;
		relations = new ArrayList<Relation>();

	}
	
	

	public Container( int k, double[] f){
		this(f);
		correctKlass = k;
	}


	abstract ArrayList<? extends Container> getRelated();

}
