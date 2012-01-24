import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Client26 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Pattern> training = new Reader( "ring.csv" ).getDatasets();

	}

}
