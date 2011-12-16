
public class OutputLayer extends Layer{

	public Vector e;
	
	public OutputLayer(int prevK, int k){
		super(prevK, k);
	}
	
	
	public void calcE(Vector teaching){
		e = (Vector) o.minus(teaching);
	}
}
