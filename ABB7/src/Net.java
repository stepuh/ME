import java.util.ArrayList;

import Jama.Matrix;


public class Net {
	ArrayList<Layer> hiddenLayers;
	OutputLayer outputLayer;
	
	public Net(int inputN, int outputM){
		hiddenLayers = new ArrayList<Layer>();
		
	}
	
	public Layer getHiddenLayer(int i){
		return hiddenLayers.get(i);
	}
	
	public void addHiddenLayer(int k){
		int prevK = hiddenLayers.get(hiddenLayers.size()-1).k;
		Layer l = new Layer(prevK, k);
		hiddenLayers.add(l);
	}
	
	public void learnFrom(Pattern input){
		//TODO:
	}
	
	public Vector getResult(Pattern input){
		Vector temp_o = new Vector(input.features);
		for(Layer l: hiddenLayers){
			temp_o = l.calc(temp_o);
		}
		return outputLayer.calc(temp_o);
	}
	
	private void feedForward( Pattern input){
		Vector temp_o = input.features;
		for(Layer l: hiddenLayers){
			temp_o = l.calcAndSave(temp_o);
		}
		outputLayer.calcAndSave(temp_o);
	}
	
	private void backPropagation(Vector teaching){
		double learnConst = 1.0;
		
		// Backpropagation-Step for the output-layer
		outputLayer.calcE(teaching);
		outputLayer.delta = new Vector(outputLayer.derivations.times(outputLayer.e));

		// update weights of output-layer
		Matrix diffWExtTransposed = outputLayer.delta.times(-learnConst).times(outputLayer.o.getExtended());
		diffWExtTransposed.transpose();
		outputLayer.wExt.minus(diffWExtTransposed);
		
		
		// Backpropagation step for hidden layers
		Layer l_succ = outputLayer; // successor Layer
		for(int i = hiddenLayers.size()-1; i>0; i--){
			Layer l = hiddenLayers.get(i);
			// calc delta
			l.delta = new Vector(l.derivations.times(l_succ.getW()).times(l_succ.delta));

			// update weights
			diffWExtTransposed = l.delta.times(-learnConst).times(l.o.getExtended()); 
			diffWExtTransposed.transpose();
			l.wExt.minus(diffWExtTransposed);
			l_succ = l;
		}	
	}
}
