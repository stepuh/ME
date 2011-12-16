
public class OutputLayer extends Layer{

	Vector e;
	
	public OutputLayer(int prevK, int k){
		super(prevK, k);
	}
	
	
	public void calcE(Vector teaching){
		double[] tmpE  = new double[k];
		double[] tmpTeaching = teaching.toArray();
		double[] tmpO = o.toArray();
		
		for(int i=0; i < k; i++){
			tmpE[i] = tmpO[i] - tmpTeaching[i];
		}
		e = new Vector(tmpE);
	}
}
