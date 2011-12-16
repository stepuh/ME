
public class OutputLayer extends Layer{

	public Vector e;
	
	
	// Creates a new Output-Layer that has k nodes
	// and which's predesessor has prevK nodes
	public OutputLayer(int prevK, int k){
		super(prevK, k);
	}
	
	
	// Calculates the vector of square-differentials between output and teaching
	public void calcE(Vector teaching){
		e = new Vector(o.minus(teaching));
	}
}
