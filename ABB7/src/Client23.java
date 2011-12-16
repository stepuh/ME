import java.util.ArrayList;


public class Client23 {

	//XOR
	public static void main(String[] args) {
		
		ArrayList<Pattern> train = new ArrayList<Pattern>();
		ArrayList<Pattern> test = new ArrayList<Pattern>();
		int dim = train.get(0).dim;
		
		Net net = new Net(dim, 1);
		for(Pattern d: train){
			net.learnFrom(d);
		}

		//TODO: count correct
		for(Pattern d: test){
			net.getResult(d);
		}
	}

}
