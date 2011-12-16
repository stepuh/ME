import java.util.ArrayList;


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
		Vector temp_o = new Vector(input.features);
		for(Layer l: hiddenLayers){
			temp_o = l.calcAndSave(temp_o);
		}
		outputLayer.calcAndSave(temp_o);
	}
	
	private void backPropagation(){
		//TODO:
	}
}