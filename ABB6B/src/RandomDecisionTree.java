

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class RandomDecisionTree {
	double entropyS;
	ArrayList<Dataset> s;
	int smallM;
	int bigN;
	int bigM;
	
	
	private Node root;

    private class Node {

        private Node parent;
        private List<Node> children;
        int splitAttr;
        int klass;
        
        public Node(ArrayList<Dataset> matchingData, HashSet<Integer> usedAttributes){
    		
        	HashMap<Integer, Integer> klassProperties = getKlassProperties(matchingData);

    		if(usedAttributes.size() == smallM || 1 == klassProperties.keySet().size() ){
    			// ich bin blatt
    			klass = -1;
    			int bestValue = -1;
    			for(Integer key: klassProperties.keySet()){
    				int tmp = (int) klassProperties.get(key);
    				if( tmp > bestValue){
    					klass = (int) key;
    					bestValue = tmp;
    				}
    			}
    			
    		System.out.println("blatt!");	
    			
    		}else{
    			// ich bin knoten
	        	HashSet<Integer> tmpAttributes = getTmpAttr( usedAttributes );
	    		
	    		
	    		splitAttr = getBestAttribute(tmpAttributes);
	    		
	    		
	    		usedAttributes.add(new Integer(splitAttr));
	    		   		
	    		HashSet<Double> values = getValues(matchingData, splitAttr);
	            children = new ArrayList<Node>();
	            for(Double value: values){
	            	Node child = new Node( getSv(matchingData, value, splitAttr), usedAttributes );
	            	child.parent = this;
	            	children.add( child );
	            }
    		}
        }

    }
	
	
	RandomDecisionTree(ArrayList<Dataset> training, int smallM ){
		this.smallM = smallM;
		this.bigN = training.size();
		this.bigM  = training.get(0).features.length;
		
		// baue eine zuf�llige Menge S mit zur�cklegen
		s = new ArrayList<Dataset>();
		for(int i=0; i < bigN; i++){
			s.add( training.get( (int) (Math.random()*bigN) ) ); // ziehen mit zur�cklegen
		}
		
		// hier kommt der eigentliche Aufbau des Baumes
		entropyS = getEntropy(s);
		
		HashSet<Integer> usedAttributes = new HashSet<Integer>(); // leere Menge {}!!
		root = new Node( s, usedAttributes );
		
	}
	
	private HashMap<Integer, Integer> getKlassProperties(ArrayList<Dataset> data){
		HashMap<Integer, Integer> klassProperties = new HashMap<Integer, Integer>();
		for(Dataset d: data){
			Integer klass = d.correctKlass;		
			// increment if klass exists
			if(klassProperties.containsKey( klass ) ){
				Integer tmp = klassProperties.get(klass);
				tmp++;
				klassProperties.put(klass, tmp);
			} 
			// if klass does not exist, create it!
			else{
				klassProperties.put(klass, new Integer(1) );
			}	
		}
		return klassProperties;
	}
	
	
	
	private double getEntropy(ArrayList<Dataset> data){
		HashMap<Integer, Integer> klassProportions = getKlassProperties(data);
		
		// calculate entropy on klass proportions
		double entropy = 0;
		Set<Integer> klasses = klassProportions.keySet();
		// System.out.println("Number of Symbols: "+klasses.size());
		double size = (double) data.size();
		//System.out.println("klasses.size()="+size);
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
		HashSet<Double> values = getValues(s, attribute);
		
		// make the gain calculation: Sum over values[ |Sv| / |S| * Entropy(sV) ]
		double sum = 0;
		for(double value: values){
			ArrayList<Dataset> sV = getSv( s, value, attribute);
			sum += ( (double)sV.size() / (double)s.size() ) * getEntropy(sV);	
		}
		double gain = entropyS - sum;
		return gain;
	}
	
	private int getBestAttribute(HashSet<Integer> attributes){
		int bestAttr = -1;
		double bestGain = 0;
		for(Integer attr: attributes){
			double gain = getGain((int) attr, s);
			if( bestGain < gain){
				bestGain = gain;
				bestAttr = (int) attr;
			}
		}
		return bestAttr;
	}
	
	private HashSet<Double> getValues(ArrayList<Dataset> datasets, int attribute){
		HashSet<Double> values = new HashSet<Double>();
		for(Dataset d: datasets){
			values.add(d.features[attribute]);
		}
		return values;
	}
	
    private HashSet<Integer> getTmpAttr( HashSet<Integer>usedAttributes ){
		HashSet<Integer> tmpAttributes = new HashSet<Integer>();
		for(int i=0; i< smallM; i++){
			boolean doItAgain = true;
			while(doItAgain){
				Integer tmp = new Integer( (int) (Math.random()*bigM) );
				if( tmpAttributes.add( tmp ) && !usedAttributes.contains(tmp) ){
					doItAgain = false;
				}
			}
		}
		return tmpAttributes;
    }
	
}
