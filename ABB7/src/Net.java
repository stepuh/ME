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
		//TODO:
	}
	
	public void learnFrom(Pattern input){
		//TODO:
	}
	
	public void getResult(Pattern input){
		//TODO:
	}
	
	private void feedForward(){
		//TODO:
	}
	
	private void backPropagation(){
		//TODO:
	}
}
