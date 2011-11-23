/*
 * Relations are a combination of Dataset and Prototype.
 * Each relation has a probability that represents the
 * probability that the Dataset is a member of the prototype's cluster.
 */

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