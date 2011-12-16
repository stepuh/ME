import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Client24 {

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<Pattern> train = new Reader("pendigits-training.txt").getDatasets();
		ArrayList<Pattern> test = new Reader("pendigits-testing.txt").getDatasets();
		int dim = train.get(0).dim;
		
		Net net = new Net(dim, 10);
		net.addHiddenLayer(10);
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
