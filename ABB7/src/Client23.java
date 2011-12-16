import java.util.ArrayList;


public class Client23 {

	//XOR
	public static void main(String[] args) {
		
		ArrayList<Pattern> train = new ArrayList<Pattern>();
		
		Vector teachingTrue = new Vector(new double[]{1});
		Vector teachingFalse = new Vector(new double[]{0});
		train.add(new Pattern(teachingFalse, new double[]{ 0,0 }) );
		train.add(new Pattern(teachingTrue, new double[]{ 1,0 }) );
		train.add(new Pattern(teachingTrue, new double[]{ 0,1 }) );
		train.add(new Pattern(teachingFalse, new double[]{ 1,1 }) );

		ArrayList<Pattern> test = new ArrayList<Pattern>();
		test.add(new Pattern(teachingTrue, new double[]{ 0,0 }) );
		test.add(new Pattern(teachingTrue, new double[]{ 1,0 }) );
		test.add(new Pattern(teachingTrue, new double[]{ 0,1 }) );
		test.add(new Pattern(teachingTrue, new double[]{ 1,1 }) );
		
		
		
		Net net = new Net(2, 1);
		net.addHiddenLayer(5);
		
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
