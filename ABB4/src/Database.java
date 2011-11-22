import java.util.ArrayList;
import java.util.Collection;

public class Database{
	
	int dimensions;
	Relation mostProbable;
	ArrayList<Dataset> datasets;
	ArrayList<Prototype> prototypes;
	ArrayList<Relation> relations;
	
	
	
	// This constructor builds a dataset list and empty relations and prototype lists
	Database(ArrayList<Dataset> d){
		datasets = d;
		dimensions = datasets.get(0).features.length;
		
		prototypes = new ArrayList<Prototype>();
		relations = new ArrayList<Relation>();
	}
	

	
	// overload constructor to handle an additional prototype list
	Database(ArrayList<Dataset> d, ArrayList<Prototype> p){
		this(d);
		this.addPrototypes(p);
	}



	// create and bind a new relation
	public boolean addRelation( Dataset d, Prototype p ){
		Relation r = new Relation( d, p);
		p.relations.add(r);
		relations.add( r );
		return true;
	}
	
	
	
	// add one new prototype by creating and binding a new relation
	public void addPrototypes( Prototype p ){
		prototypes.add( p );
		for( Dataset d: datasets )
			addRelation( d, p );
	}
	
	
	
	// overload method to work with lists
	public boolean addPrototypes( Collection<Prototype> prototypes ){
		for( Prototype p: prototypes ){
			addPrototypes(p);
		}
		return true;
	}


}
