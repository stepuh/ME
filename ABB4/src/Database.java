import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



public class Database implements List{
	
	int dimensions;
	Relation mostProbable;
	ArrayList<Dataset> datasets;
	ArrayList<Dataset> prototypes;
	ArrayList<Relation> relations;
	
	Database(ArrayList<Dataset> d){
		datasets = d;
		dimensions = datasets.get(0).features.length;
		
		prototypes = new ArrayList<Dataset>();
		relations = new ArrayList<Relation>();
	}
	

	public boolean add(Dataset d, Dataset p){
		// create and bind relation
		Relation r = new Relation( d, p, 0);
		d.relations.add(r);
		p.relations.add(r);
		relations.add( r );
		return true;
	}
	
	public void addPrototype(Dataset p){
		prototypes.add(p);
		for(Dataset d: datasets)
			add(d, p);
	}
	
	public boolean addAllPrototypes(Collection<Dataset> prototypes){
		for(Dataset p: prototypes){
			addPrototype(p);
		}
		return true;
	}

	
	public void calcProbabilities(){
		
	}	
	
	
	// to add List support
	// we need all the following methods. 
	
	public void add(int arg0, Object arg1) {
		relations.add(arg0, (Relation) arg1);
	}

	public boolean addAll(Collection arg0) {
		return relations.addAll(arg0);
	}

	public boolean addAll(int arg0, Collection arg1) {
		return relations.addAll(arg0, arg1);
	}

	public void clear() {
		datasets.clear();
		prototypes.clear();
		relations.clear();
	}

	public boolean contains(Object arg0) {
		return relations.contains(arg0);
	}

	public boolean containsAll(Collection arg0) {
		return relations.containsAll(arg0);
	}

	public Relation get(int arg0) {
		return relations.get(arg0);
	}

	public int indexOf(Object arg0) {
		return relations.indexOf(arg0);
	}

	public boolean isEmpty() {
		return relations.isEmpty();
	}

	public Iterator<Relation> iterator() {
		return relations.iterator();
	}

	public int lastIndexOf(Object arg0) {
		return relations.lastIndexOf(arg0);
	}

	public ListIterator<Relation> listIterator() {
		return relations.listIterator();
	}

	public ListIterator<Relation> listIterator(int arg0) {
		return relations.listIterator(arg0);
	}

	public boolean remove(Object arg0) {
		return relations.remove(arg0);
	}

	public Relation remove(int arg0) {
		return relations.remove(arg0);
	}

	public boolean removeAll(Collection arg0) {
		return relations.removeAll(arg0);
	}

	public boolean retainAll(Collection arg0) {
		return relations.retainAll(arg0);
	}

	public Object set(int arg0, Object arg1) {
		return relations.set(arg0, (Relation) arg1);
	}

	public int size() {
		return relations.size();
	}

	public List<Relation> subList(int arg0, int arg1) {
		return relations.subList(arg0, arg1);
	}

	public Object[] toArray() {
		return relations.toArray();
	}

	public Object[] toArray(Object[] arg0) {
		return relations.toArray(arg0);
	}

	public boolean add(Object r) {
		if( r instanceof Relation){
			relations.add((Relation) r);
			return true;
		}else{
			return false;
		}
	}

}
