import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RandomDecisionTree {
	double entropyS;
	ArrayList<Dataset> s;
	
	private Node root;

    private class Node {
        private double value;
        private Node parent;
        private List<Node> children;
    }
	
	
	RandomDecisionTree(ArrayList<Dataset> training, int smallN ){
		int bigN = training.size();
		int dim  = training.get(0).dim;
		
		// baue eine zufällige Menge S mit zurücklegen
		s = new ArrayList<Dataset>();
		for(int i=0; i < bigN; i++){
			s.add( training.get( (int) (Math.random()*bigN) ) ); // ziehen mit zurücklegen
		}
		
		// Wähle zufällig smallN _verschiedene_ Attribute,
		HashSet<Integer> attributes = new HashSet<Integer>();
		for(int i=0; i< smallN; i++){
			boolean doItAgain = true;
			while(doItAgain){
				if(attributes.add( new Integer( (int) (Math.random()*dim) ) ) ){
					doItAgain = false;
				}
			}
		}

		
		// hier kommt der eigentliche Aufbau des Baumes
		entropyS = getEntropy(s);
		
		int firstAttr = getBestAttribute(attributes);
		System.out.println("Attribute: "+firstAttr);
		
		root = new Node();
       // root.value = rootValue;
        root.children = new ArrayList<Node>();
	}
	
	private double getEntropy(ArrayList<Dataset> data){
		HashMap<Integer, Integer> klassProportions = new HashMap<Integer, Integer>();
		for(Dataset d: data){
			Integer klass = d.correctKlass;		
			// increment if klass exists
			if(klassProportions.containsKey( klass ) ){
				Integer tmp = klassProportions.get(klass);
				tmp++;
				klassProportions.put(klass, tmp);
			} 
			// if klass does not exist, create it!
			else{
				klassProportions.put(klass, new Integer(1) );
			}	
		}
		// calculate entropy on klass proportions
		double entropy = 0;
		Set<Integer> klasses = klassProportions.keySet();
		// System.out.println("Number of Symbols: "+klasses.size());
		double size = (double) klasses.size();
		for(Integer klass: klasses){
			double value = (double) klassProportions.get(klass);
			double pi = (double) value / size;
			double logPi = Math.log(pi) ;
			// System.out.println("Klasse: "+klass+" Value: "+value+" Proportion: "+pi+" logPi: "+logPi);
			entropy += ( pi * logPi );
		}
		return -entropy; // make it negative!
	}
	
	private ArrayList<Dataset> getSv(ArrayList<Dataset> s, double  value, int attribute){
		ArrayList<Dataset> subset = new ArrayList<Dataset>();
		for(Dataset d: s){
			if(d.features[attribute] == value){
				subset.add(d);
			}
		}
		return subset;
	}
	
	private double getGain(int attribute, ArrayList<Dataset> s){
		// get the set of attribute values
		HashSet<Double> values = new HashSet<Double>();
		for(Dataset d: s){
			values.add(d.features[attribute]);
		}
		
		// make the gain calculation: Sum over values[ |Sv| / |S| * Entropy(sV) ]
		double sum = 0;
		for(double value: values){
			ArrayList<Dataset> sV = getSv( s, value, attribute);
			sum += (double)sV.size() / (double)s.size() * getEntropy(sV);	
		}
		double gain = entropyS - sum;
		return gain;
	}
	
	private int getBestAttribute(HashSet<Integer> attributes){
		int bestAttr = -1;
		double bestGain = 0;
		for(Integer attr: attributes){
			double gain = getGain((int) attr, s);
			if( bestGain > gain){
				bestGain = gain;
				bestAttr = (int) attr;
			}
		}
		return bestAttr;
	}
	
}
