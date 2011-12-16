import java.util.ArrayList;

import Jama.Matrix;


public class Client22 {


	public static void main(String[] args) {
		ArrayList<Pattern> test = new ArrayList<Pattern>();
		Vector t = new Vector(new double[]{1.});
		test.add(new Pattern(t, new double[]{ 0,0 }) );
		test.add(new Pattern(t, new double[]{ 1,0 }) );
		test.add(new Pattern(t, new double[]{ 0,1 }) );
		test.add(new Pattern(t, new double[]{ 1,1 }) );
		
		
		Net net = new Net(2, 1);
		net.addHiddenLayer(2);
		
		
		double w1Arr[][] = new double[3][];
		w1Arr[0] = new double[]{-4.081, 4.111, -2.439};
		w1Arr[1] = new double[]{6.034, -5.844, -3.297};
		w1Arr[2] = new double[]{3.688, -3.131, 1.810};
		Matrix w1 = new Matrix(w1Arr);
		Matrix w2 = new Matrix(new double[]{6.053, 9.059, -4.517, -0.463},1).transpose();
		
		
		
		net.getHiddenLayer(0).wExt = w1;
		net.outputLayer.wExt = w2;

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
