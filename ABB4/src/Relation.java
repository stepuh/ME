public class Relation {
	public Dataset prototype;
	public Dataset dataset;	
	public double probability;
	
	Relation(Dataset d, Dataset proto, double prob){
		prototype = proto;
		dataset = d;
		probability = prob;
	}
}