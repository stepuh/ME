import java.util.Arrays;
import java.util.LinkedList;


public class Netz {

	// Attribute
	public LinkedList<Neuron> inputNeuronen = new LinkedList<Neuron>();
	public LinkedList<LinkedList<Neuron>> innereNeuronen = new LinkedList<LinkedList<Neuron>>();
	public LinkedList<Neuron> outputNeuronen = new LinkedList<Neuron>();

	private double standardBias = -0.5;
	private double[] error;
	
	

	
	/* 
	 * Erstellt eine mehrschichtiges neuronales Netz.
	 * Dabei gibt es anzInput viele Input-Neuronen, 
	 * und anzSchichten viele Schichten. Die Inneren
	 * Schichten haben dabei jeweils hoeheSchichten viele Neuronen.
	 * Zuletzt gibt es noch die Output-Schicht,
	 * welche anzOutput viele Neuronen hat.
	 * Achtung: anzSchichten ist exklusiv Input-/Output-Schicht.
	 */
	public Netz(int anzInput, int anzSchichten, int hoeheSchichten, int anzOutput){
		// Input-Neuronen erstellen + 1 Bias-Neuron
		for (int i = 0; i < anzInput+1; i++) {
			// Input-Layer
			Neuron n = new Neuron();
			inputNeuronen.add(n);
			n.istKonstant = true;
		}
		inputNeuronen.get(anzInput).standard_output = 1.0;
		
		// Zum Vernetzen der einzelnen Neuronen merken wir uns stets die Vorgängerschicht
		LinkedList<Neuron> vorgaengerSchicht = inputNeuronen;
		
		// Innere Neuronen erstellen + 1 Bias-Neuron
		for (int i = 0; i < anzSchichten+1; i++){
			// Neue Neuronenschicht erstellen und mit Neuronen füllen
			LinkedList<Neuron> schicht = new LinkedList<Neuron>();
			for (int j = 0; j < hoeheSchichten; j++){
				Neuron n = new Neuron();
				schicht.add(n);				
				
				// Vorgängerschicht mit neu erstellter Schicht vernetzen
				for(Neuron v : vorgaengerSchicht){
					double gewicht = Math.random() * 2.0 -1.0; // [-1,1]
					
					// Sonderfall: es handelt sich um das Bias-Neuron -> konstanter negativer Schwellenwert.
					if (v == vorgaengerSchicht.get(anzInput)){
						v.istKonstant = true;
						gewicht = standardBias;
					}
					
					v.erstelleSynapse(n, gewicht);
				}
			}
			innereNeuronen.add(schicht);
			vorgaengerSchicht = schicht;
		}
		
		// Output-Neuronen erstellen
		for (int i = 0; i < anzOutput; i++) {
			Neuron n = new Neuron();
			outputNeuronen.add(n);

			// Vorgängerschicht mit Output-Schicht vernetzen
			for (Neuron v : vorgaengerSchicht) {
				double gewicht = Math.random() * 2.0 - 1.0; // [-1,1]				
			
				// Sonderfall: es handelt sich um das Bias-Neuron -> konstanter negativer Schwellenwert.
				if (v == vorgaengerSchicht.get(anzInput)){
					v.istKonstant = true;
					gewicht = standardBias;
				}

				v.erstelleSynapse(n, gewicht);
			}
		}
	}
	

	
	// Setzt das Gewicht einer Kante zwischen zwei Neuronen
	// und liefert true, falls dies erfolgreich passiert ist.
	public boolean setzeGewicht(Neuron start, Neuron ziel, double gewicht){
		for(Synapse s : start.outputSynapsen){
			if (s.zu == ziel){
				s.gewicht = gewicht;
				return true;
			}
		}
		return false;
	}
	
	
	
	// Setzt die Standardmäßigen Outputs der Input-Neuronenschicht.
	public void musterAnlegen(Muster m){
		for (int i = 0; i < m.input.length; i++) {
			inputNeuronen.get(i).standard_output = m.input[i];
		}
	}
	
	
	
	// Das Netz liefert das Ergebnis zu einem Muster
	public double[] ergebnisZuMuster(Muster m) {
		musterAnlegen(m);
		
		// Ergebnisse ausrechnen
		double[] ergebnis = new double[outputNeuronen.size()];
		for (int i = 0; i < outputNeuronen.size(); i++) {
			ergebnis[i] = outputNeuronen.get(i).out();
		}

		return ergebnis;
	}

	
	
	// Das Netz liefert das Ergebnis zu einer Menge von Mustern
	public void ergebnisZuMuster_all(LinkedList<Muster> muster) {
		for (Muster m : muster) {
			musterAnlegen(m);
			
			// Ergebnisse ausrechnen
			double[] ergebnis = new double[outputNeuronen.size()];
			for (int i = 0; i < outputNeuronen.size(); i++) {
				ergebnis[i] = outputNeuronen.get(i).out();
			}

			System.out.println(Arrays.toString(ergebnis));
		}
	}

	
	
	// Ausgehend von dem Eingabevektor,  der erzeugten Ausgabe des Netzes und der gewünschten Ausgabe,
	// wird hier ein Error-Vektor berechnet
	private double teilErrorBerechnen(Muster m) {
		// Muster auf Netz uebertragen
		musterAnlegen(m);
		
		// Ergebnisse und Error-Werte ausrechnen
		double[] ergebnis = new double[outputNeuronen.size()];
		double[] error = new double[outputNeuronen.size()];
		for (int i = 0; i < outputNeuronen.size(); i++) {
			ergebnis[i] = outputNeuronen.get(i).out();
			error[i] = m.teaching[i] - ergebnis[i];
		}
		
		double wert = vektorLaenge(error);
		return 0.5*Math.pow(wert, 2);
	}

	
	
	private double gesamtErrorBerechnen(LinkedList<Muster> mustermenge){
		double wert = 0.0;
		for(Muster m : mustermenge){
			wert += teilErrorBerechnen(m); 
		}
		return wert;
	}
	
	
	
	private double vektorLaenge(double[] vektor){
		double wert = 0.0;
		for(int i=0; i<vektor.length; i++){
			wert += vektor[i];
		}
		return Math.sqrt(wert);
	} 
	
	
//	
//	
//	// Das Netz lernt nach der Delta-Regel eine Menge von Muster
//	public int lernen_all(LinkedList<Muster> muster) {
//		boolean failed = true;
//		// sollte er laenger brauchen, um dieses Muster zu lernen, ist es
//		// wahrscheinlich nicht linear trennbar
//		int maxIterationen = 1000;
//		int currentIteration = 0;
//
//		while (failed && currentIteration < maxIterationen) {
//			failed = false;
//			currentIteration++;
//
//			for (Muster m : muster) {
//				boolean thisFailed = lernen(m);
//				if (thisFailed){
//					failed = true;
//				}
//			}
//		}
//
//		// Liefere die Anzahl benoetigter Lernschritte, um das Muster zu
//		// erlernen
//		
//		if (currentIteration > maxIterationen - 5 ){
//			System.out.println("FAIL!");
//		}
//		
//		return currentIteration;
//	}
//
//	
//	
//	// Das Netz lernt nach der Delta-Regel eine Menge von Muster
//	public int pocket_lernen(LinkedList<Muster> muster) {
//		int failes = 1;
//		int minFailes = 100000;
//		double[][] besteGewichte = new double[inputNeuronen.size()][outputNeuronen.size()];
//		
//		// sollte er laenger brauchen, um dieses Muster zu lernen, ist es
//		// wahrscheinlich nicht linear trennbar
//		int maxIterationen = 1000;
//		int currentIteration = 0;
//
//		while (failes > 0 && currentIteration < maxIterationen) {
//			currentIteration++;
//			failes = 0;
//			
//			// Veraendere die Gewichte fuer alle Muster
//			for (Muster m : muster) {
//				boolean thisFailed = lernen(m);
//				if (thisFailed){
//					failes++;
//				}
//			}
//
//			// War dieser Durchlauf besser als die davor?
//			// Wenn ja, dann speichere dir die Gewichte.
//			if (failes < minFailes){
//				minFailes = failes;
//				int iCount = 0;
//				int oCount = 0;
//
//				for(Neuron o : outputNeuronen){
//					for(Synapse s : o.dendriten){
//						besteGewichte[iCount][oCount] = s.gewicht; 
//						iCount++;
//					}
//					iCount=0;
//					oCount++;
//				}
//				
//			}
//		
//		}
//
//		// Nutze die gespeicherten Gewichte, sollte der Algorithmus nicht terminiert sein
//		if (currentIteration >= maxIterationen){
//			int iCount = 0;
//			int oCount = 0;
//			for(Neuron o : outputNeuronen){
//				for(Synapse s : o.dendriten){
//					s.gewicht = besteGewichte[iCount][oCount]; 
//					iCount++;
//				}
//				iCount=0;
//				oCount++;
//			}
//			
//		}
//		
//		
//		// Liefere die Anzahl benoetigter Lernschritte, um das Muster zu
//		// erlernen
//		return currentIteration;
//	}
//		
//
//	

}
