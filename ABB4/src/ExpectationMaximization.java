import java.util.ArrayList;


public class ExpectationMaximization {
	Database db;
	
	ExpectationMaximization(Database r){
		db = r;
	}
	
	public void run(){
		
		//terminate
		double lastRound = round();
		double currentRound = round();
		while( lastRound > currentRound){
			lastRound = currentRound;
			lastRound = round();
		}
	}
	
	public double round(){
		// expectation
		expectation();
		
		// maximization
		
		for(Dataset p: db.prototypes){
			// calc myu
			double[] myu = LinearAlgebra.getMyu( p );
			// calc pi
			double pi  = LinearAlgebra.getPi( p );
			// calc S
			double[][] s = LinearAlgebra.getS( p );
			
			
			
		}
		
		
		// estimate costs
		
		return 1;
	}
	
	public void expectation(){
		// expectation = Math.pow((2.0*Math.PI), -dimensions / 2.0) * Math.pow(prototype.S.det(), -0.5);
		double leftFactor = Math.pow(2.0 * Math.PI, -(db.dimensions) / 2.0);

	}
}
