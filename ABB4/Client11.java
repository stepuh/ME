import java.util.ArrayList;

public class Client11 {
	
	
	public static void main(String[] args) throws Exception{
		// reads train data out of file
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();

		
		Database db = new Database(training);
		
		// we want to have k clusters
		int k = 10;
		
		// initializes the clusters by running k-means
		KMeans km = new KMeans(db, k);
		km.rounds = 10;
		km.run();
		

		// initialize so we can use Gauss
		for(Prototype p: db.prototypes){
			System.out.println(p.correctKlass);
		}
		
		db.initGauss();
		for(Prototype p: db.prototypes){
			System.out.println(p.correctKlass);
		}
		
		// calculate fischer's discriminant
//		Fischer f = new Fischer(db);
//		
//		for(int i= 0; i<10; i++){
//			Prototype proto = db.prototypes.get(i);
//			Dataset data = db.datasets.get(0);
//			boolean klass = f.getKlass(proto, data); // true: same class, false: different class
//			System.out.println(klass+" "+data.correctKlass+" "+proto.correctKlass);
//		}

	
	
		
		

	}
}
