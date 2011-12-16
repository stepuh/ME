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

		int total = 0;
		int correct = 0;
		for(Pattern d: test){
			total++;
			double[] result = net.getResult(d).toArray();
			double[] teaching = d.teaching.toArray();
			boolean corr = true;
			for(int i=0; i<result.length; i++){
				if( result[i] != teaching[i]){
					corr = false;
				}	
			}
			if(corr){
				correct++;
			}
		}
		System.out.println( (correct/(double) total *100) + "% korrekt");
	}

}
