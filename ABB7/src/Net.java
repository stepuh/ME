import java.util.ArrayList;

import Jama.Matrix;


public class Net {
	
	ArrayList<Layer> hiddenLayers;
	OutputLayer outputLayer;
	
	
	
	// Creates a new Net with an input-size n and an output-size m
	// The net has no hidden layers at this point
	public Net(int inputN, int outputM){
		hiddenLayers = new ArrayList<Layer>();
		outputLayer = new OutputLayer(inputN, outputM);
		
	}
	
	
	
	// Returns the hidden layer with index i
	public Layer getHiddenLayer(int i){
		return hiddenLayers.get(i);
	}
	
	
	// Adds a new hidden layer right after the last hidden layer with k nodes
	public void addHiddenLayer(int k){
		int prevK;
		if(hiddenLayers.size()>1){
			prevK = hiddenLayers.get(hiddenLayers.size()-1).k;
		}else{
			prevK = outputLayer.prevK;
		}
		
		Layer l = new Layer(prevK, k);
		hiddenLayers.add(l);
		int m = outputLayer.k;
		outputLayer = new OutputLayer(k, m);
	}
	
	
	
	// Updates the net's weights after learning from a certain pattern
	public void learnFrom(Pattern pattern){
		feedForward(pattern.features);
		backPropagation(pattern.teaching, pattern.features);
	}
	
	
	
	// Returns the result of the output-vector for a certain input-vector
	public Vector getResult(Pattern input){
		Vector temp_o = new Vector(input.features);
		for(Layer l: hiddenLayers){
			temp_o = l.calc(temp_o);
		}
		double[] ecklig = outputLayer.calc(temp_o).toArray();
		double maxValue = 0;
		int maxPos = 0;
		for(int i=0; i<ecklig.length; i++){
			if(ecklig[i]> maxValue){
				maxValue = ecklig[i];
				maxPos = i;
			}
		}
		Matrix tmp = new Matrix(ecklig.length, 1);
		tmp.set(maxPos, 0, 1);
		return new Vector(tmp);
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
	private void backPropagation(Vector teaching, Vector input){
		double learnConst = 1.0;
		
		// Backpropagation-Step for the output-layer
		outputLayer.calcE(teaching);
		Matrix d = outputLayer.derivations;
		System.out.println("d " + d.getRowDimension() +", "+d.getColumnDimension());

		
		Matrix e = outputLayer.e;
		System.out.println("e " + e.getRowDimension() +", "+e.getColumnDimension());
		
		outputLayer.delta = new Vector(outputLayer.derivations.times(outputLayer.e.transpose()).transpose());
		

		//
		Vector prevLayer = hiddenLayers.get(hiddenLayers.size()-1).o;
		
		// update weights of output-layer
		System.out.println("wExt " + outputLayer.wExt.getRowDimension() +", "+outputLayer.wExt.getColumnDimension());
		Matrix diffWExtTransposed = outputLayer.delta.transpose().times(-learnConst).times(prevLayer.getExtended());
		System.out.println("diff " + diffWExtTransposed.getRowDimension() +", "+diffWExtTransposed.getColumnDimension());

		outputLayer.wExt = outputLayer.wExt.plus(diffWExtTransposed.transpose());
		
		System.out.println("#hidden: "  + hiddenLayers.size());
		
		// Backpropagation step for hidden layers
		Layer l_succ = outputLayer; // successor Layer
		for(int i = hiddenLayers.size()-1; i>=0; i--){
			System.out.println("i:" + i);
			Layer l = hiddenLayers.get(i);
			// calc delta
			

			
			int cols = l.derivations.getColumnDimension();
			int rows = l.derivations.getRowDimension();
			System.out.println("derivations c: "+ cols + "r: " + rows);

			cols = l_succ.getW().getColumnDimension();
			rows = l_succ.getW().getRowDimension();
			System.out.println("suc.getW c: "+ cols + "r: " + rows);
			
			cols = l_succ.delta.getColumnDimension();
			rows = l_succ.delta.getRowDimension();
			System.out.println("succ_delta c: "+ cols + "r: " + rows);

			
			
			l.delta = new Vector(l.derivations.times(l_succ.getW()).times(l_succ.delta.transpose()).transpose());
			
			
			
			if(i > 0){
				prevLayer = hiddenLayers.get(i-1).o;
			}else{
				prevLayer = input; 
			}
			
			// update weights
			diffWExtTransposed = l.delta.transpose().times(-learnConst).times(prevLayer.getExtended());
			diffWExtTransposed = diffWExtTransposed.transpose();

			
			cols = diffWExtTransposed.getColumnDimension();
			rows = diffWExtTransposed.getRowDimension();
			System.out.println("diffWExtT c: "+ cols + "r: " + rows);
			
			cols = l.wExt.getColumnDimension();
			rows = l.wExt.getRowDimension();
			System.out.println("l.wExt c: "+ cols + "r: " + rows);

			l.wExt = l.wExt.plus(diffWExtTransposed);
			System.out.println("#####################");
			l_succ = l;
		}	
	}
}
