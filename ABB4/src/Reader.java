import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;




public class Reader {
	
	public ArrayList<Dataset> data;
	
	
	public Reader(String path) throws FileNotFoundException{
		File f = new File( path );
		Scanner scanner = new Scanner( f );
		
		data = new ArrayList<Dataset>();

		while( scanner.hasNext() ){
			// parse lines
			String line = scanner.nextLine().trim(); 
			String[] stringParts = line.split("\\s+");
			
			int correctKlass = Integer.parseInt( stringParts[ stringParts.length - 1]);
			
			double [] features = new double[ stringParts.length-1 ];
			for(int i=0; i < stringParts.length-1; i++){
				features[i] = Double.parseDouble( stringParts[i] );
			}
			
			data.add(new Dataset(correctKlass, features));
		}
	}
	
	public ArrayList<Dataset> getDatasets(){
		return data;
	}
	

}
