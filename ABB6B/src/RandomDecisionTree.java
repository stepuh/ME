

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
        int splitAttr;
        int klass;
        
        public Node(ArrayList<Dataset> matchingData, Random rnd ){
    		

    		if( 1 == matchingData.size() ){
    			// ich bin blatt
    			klass = matchingData.get(0).correctKlass;	
    		}else{
    			children = new ArrayList<Node>();
    			
    			// ich bin knoten
    			
	    		splitAttr = ( rnd.nextInt(bigM) );
	    		
	    		double myu = 0;
	    		for(Dataset d: matchingData){
	    			myu += d.features[splitAttr];
	    		}
	    		myu /= matchingData.size();
	    		
	    		ArrayList<Dataset> left = new ArrayList<Dataset>();
	    		ArrayList<Dataset> right = new ArrayList<Dataset>();
	    		
	    		for(Dataset d: matchingData){
	    			if( myu > d.features[splitAttr]){
	    				left.add(d);
	    			}else{
	    				right.add(d);
	    			}
	    		}
	    		//children.add( new Node( left ) );
	    		children.add( new Node( right, rnd ) ); 
	            
    		}
        }

    }
	
	
	RandomDecisionTree(ArrayList<Dataset> training, Random rnd){
		this.bigN = training.size(); // 200
		this.bigM  = training.get(0).features.length; // 180
		
		// baue eine zuf�llige Menge S mit zur�cklegen
		s = new ArrayList<Dataset>();
		for(int i=0; i < bigN; i++){
			s.add( training.get( (int) (Math.random()*bigN) ) ); // ziehen mit zur�cklegen
		}
		
		root = new Node( s, rnd );
		
	}
	

	
	

	
}
