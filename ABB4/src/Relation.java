public class Relation {
	public Prototype prototype;
	public Dataset dataset;	
	public double probability;
	public double distance;
	
	Relation(Dataset d, Prototype proto){
		prototype = proto;
		dataset = d;
	}
}