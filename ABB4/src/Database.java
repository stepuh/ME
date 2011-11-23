import java.util.ArrayList;


/*
 * The Database contains all important data that is used in EM-Algorithms
 * and Fisher-Algorithm.
 * It contains both, Datasets and prototypes as well as the relations these
 * combine. 
 */
public class Database{
	
	int dimensions;						// dimensions of vectors
	Relation mostProbable;				
	ArrayList<Dataset> datasets;		// list of all Datasets
	ArrayList<Prototype> prototypes;	// list of all Prototypes
	ArrayList<Relation> relations;		// list of all relations between Datasets and Prototypes
	
	
	
	// This constructor builds a dataset list and empty relations and prototype lists
	Database(){
		datasets = new ArrayList<Dataset>();
		prototypes = new ArrayList<Prototype>();
		relations = new ArrayList<Relation>();
	}
	
	
	
	// Overload constructor to handle a generic list as initialization
	Database( ArrayList<? extends Container> containers ) throws Exception{
		this();
		dimensions = containers.get(0).features.length;
		addAll( containers );
	}
	
	
	
	// Add a list of prototype/datasets
	public void addAll( ArrayList<? extends Container> containers ) throws Exception {
		for(Object c: containers){
			add((Container) c);
		}
	}


	
	// Determines what kind of subclass from Container c is and adds the adequate relation
	public void add( Container c) throws Exception{
		if( c instanceof Prototype ){
			Prototype p = (Prototype) c;
			prototypes.add( p );
			for( Dataset d: datasets ){
				addRelation( d, p );	
			}
		}else if( c instanceof Dataset ){
			Dataset d = (Dataset) c;
			datasets.add( d );
			for( Prototype p: prototypes ){
				addRelation( d, p );	
			}
		}else{
			throw new Exception("Added something a subclass of Container i don't know! :(");
		}
		
	}
	
	
	
	// create and bind a new relation
	private void addRelation( Dataset d, Prototype p ){
		Relation r = new Relation( d, p);
		p.relations.add(r);
		d.relations.add(r);
		relations.add( r );
	}


}
