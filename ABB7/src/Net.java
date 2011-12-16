import java.util.ArrayList;

import Jama.Matrix;


public class Net {
	
	ArrayList<Layer> hiddenLayers;
	OutputLayer outputLayer;
	
	
	
	// Creates a new Net with an input-size n and an output-size m
	// The net has no hidden layers at this point
	public Net(int inputN, int outputM){
		hiddenLayers = new ArrayList<Layer>();
		
	}
	
	
	
	// Returns the hidden layer with index i
	public Layer getHiddenLayer(int i){
		return hiddenLayers.get(i);
	}
	
	
	// Adds a new hidden layer right after the last hidden layer with k nodes
	public void addHiddenLayer(int k){
		int prevK = hiddenLayers.get(hiddenLayers.size()-1).k;
		Layer l = new Layer(prevK, k);
		hiddenLayers.add(l);
	}
	
	
	
	// Updates the net's weights after learning from a certain pattern
	public void learnFrom(Pattern pattern){
		feedForward(pattern.features);
		backPropagation(pattern.teaching);
	}
	
	
	
	// Returns the result of the output-vector for a certain input-vector
	public Vector getResult(Pattern input){
		Vector temp_o = new Vector(input.features);
		for(Layer l: hiddenLayers){
			temp_o = l.calc(temp_o);
		}
		return outputLayer.calc(temp_o);
	}
	
	
	
	// The Feed-Forward-Step calculates and saves the derivations
	// and the outputs of each node of the net
	private void feedForward(Vector input){
		Vector temp_o = input;
		for(Layer l: hiddenLayers){
			temp_o = l.calcAndSave(temp_o);
		}
		outputLayer.calcAndSave(temp_o);
	}
	
	
	
	// The Backpropagation Step calculates new Weights by
	// using the derivations and traversing backwards through the net.
	private void backPropagation(Vector teaching){
		double learnConst = 1.0;
		
		// Backpropagation-Step for the output-layer
		outputLayer.calcE(teaching);
		outputLayer.delta = new Vector(outputLayer.derivations.times(outputLayer.e));

		// update weights of output-layer
		Matrix diffWExtTransposed = outputLayer.delta.times(-learnConst).times(outputLayer.o.getExtended());
		diffWExtTransposed.transpose();
		outputLayer.wExt.plus(diffWExtTransposed);
		
		
		// Backpropagation step for hidden layers
		Layer l_succ = outputLayer; // successor Layer
		for(int i = hiddenLayers.size()-1; i>0; i--){
			Layer l = hiddenLayers.get(i);
			// calc delta
			l.delta = new Vector(l.derivations.times(l_succ.getW()).times(l_succ.delta));

			// update weights
			diffWExtTransposed = l.delta.times(-learnConst).times(l.o.getExtended()); 
			diffWExtTransposed.transpose();
			l.wExt.plus(diffWExtTransposed);
			l_succ = l;
		}	
	}
}
