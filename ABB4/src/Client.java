import java.util.ArrayList;

public class Client {
	
	
	public static void main(String[] args) throws Exception{
		int k = 10;
		
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();
		
		Database db = new Database(training);
		
		KMeans km = new KMeans(db, k);
		
		for(Prototype p: db.prototypes){
			System.out.println(p.relations.size());
		}
		

	}
}
