import java.util.ArrayList;

import Jama.Matrix;


public class Client22 {


	public static void main(String[] args) {
		ArrayList<Pattern> train = new ArrayList<Pattern>();
		ArrayList<Pattern> test = new ArrayList<Pattern>();
		int dim = train.get(0).dim;
		
		Net net = new Net(dim, 1);
		net.addHiddenLayer(2);
		
		Matrix w1 = new Matrix(3,3);
		Matrix w2 = new Matrix(1,3); // Spaltenvektor???!!!! TODO:!!!11einself
		
		net.getHiddenLayer(1).wExt = w1;
		net.outputLayer.wExt = w2;
		

		//TODO: count correct
		for(Pattern d: test){
			net.getResult(d);
		}
	}

}
