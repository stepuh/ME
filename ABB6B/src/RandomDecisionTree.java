

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
        
        Node lub;
        Node rub;
        double myu;
        int splitAttr;
        int depth;
        int klass;
        Dataset blattSet;
        
        public Node(ArrayList<Dataset> matchingData, Random rnd, int parentDepth ){
    		
        	depth = parentDepth+1;
        	if( 20 == depth ){
        		HashMap<Integer, Integer> valueMap = new HashMap<Integer,Integer>();
        		for(Dataset d: matchingData){
        			if (valueMap.containsKey(d.correctKlass)) {
        				Integer tmp = valueMap.get(d.correctKlass);
        				valueMap.put(d.correctKlass, tmp + 1);
        			} else {
        				valueMap.put(d.correctKlass, 1);
        			}
        		}
        		Set<Integer> keys = valueMap.keySet();
        		int max = 0;
        		int bestKey = 0;
        		for (Integer k1 : keys) {
        			int value = valueMap.get(k1);
        			if (max < value) {
        				max = value;
        				bestKey = k1;
        			}
        		}
        		klass = valueMap.get(bestKey);
        		
        	}
        	else if( 1 == matchingData.size() ){
    			// ich bin blatt
    			klass = matchingData.get(0).correctKlass;	
    		}else{
    			children = new ArrayList<Node>();
    			
    			// ich bin knoten
    			
    			ArrayList<Dataset> left = new ArrayList<Dataset>();
	    		ArrayList<Dataset> right = new ArrayList<Dataset>();
    			
	   
	    		left.removeAll(left);
	    		right.removeAll(right);
    			
    			splitAttr = ( rnd.nextInt(bigM) );
	    		
	    		myu = 0;
	    		for(Dataset d: matchingData){
	    			myu += d.features[splitAttr];
	    		}
	    		myu /= matchingData.size();
	    		    		
	    		for(Dataset d: matchingData){
	    			if( myu > d.features[splitAttr]){
	    				left.add(d);
	    			}else{
	    				right.add(d);
	    			}
	    		}

	    		if( 0 < left.size() ){
	    			lub = new Node( left, rnd, depth );
	    		}
	    		if( 0 < right.size() ){
	    			rub = new Node( right, rnd, depth ); 
	    		}
	            
    		}
        }
    	
        public int traverse(Dataset d){
    		// blatt
        	if (lub == null && rub == null){
    			return klass;
    		}
        	
        	// innerer knoten
        	if( myu > d.features[splitAttr]){
    			if (lub != null){
    				return lub.traverse(d);
    			}else{
    				return -1; // fail
    			}
    		}else{
    			if (rub != null){
    				return rub.traverse(d);
    			}else{
    				return -1; // fail
    			}
    		}
    	}

    }
	

    public int traverse(Dataset d){
    	return root.traverse(d);
    }
	
	RandomDecisionTree(ArrayList<Dataset> training){
		this.bigN = training.size(); // 200
		this.bigM  = training.get(0).features.length; // 180
		Random rnd = new Random();
		// baue eine zuf�llige Menge S mit zur�cklegen
		s = new ArrayList<Dataset>();
		for(int i=0; i < bigN; i++){
			s.add( training.get( (int) (Math.random()*bigN) ) ); // ziehen mit zur�cklegen
		}
		
		root = new Node( s, rnd, 0 );
		
	}
	

	
	

	
}
