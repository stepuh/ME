

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class PerzeptronNetz {

	// Attribute
	public LinkedList<Neuron> inputNeuronen = new LinkedList<Neuron>();
	public LinkedList<Neuron> outputNeuronen = new LinkedList<Neuron>();

	
	
	// Konstruktor
	public PerzeptronNetz(int in, int out) {
		// Input-Neuronen erstellen
		for (int i = 0; i < in; i++) {
			Neuron n = new Neuron();
			inputNeuronen.add(n);
		}

		// Output-Neuronen erstellen
		for (int i = 0; i < out; i++) {
			Neuron n = new Neuron();
			outputNeuronen.add(n);
		}

		// Neuronen vernetzen
		for (Neuron input : inputNeuronen) {
			for (Neuron output : outputNeuronen) {
				double gewicht = Math.random();				
				input.erstelleSynapse(output, gewicht);
			}
		}
	}

	
	
	// Das Netz lernt nach der Delta-Regel für genau einen Lernschritt und gibt
	// zurueck,
	// ob das Neuron nun das gewuenschte Ergebnis liefert.
	public boolean lernen(Muster m) {
		boolean failed = false;

		// Muster auf Netz uebertragen
		for (int i = 0; i < m.input.length; i++) {
			inputNeuronen.get(i).standard_output = m.input[i];
		}

		// Ergebnisse und Error-Werte ausrechnen
		double[] ergebnis = new double[outputNeuronen.size()];
		double[] err = new double[outputNeuronen.size()];
		for (int i = 0; i < outputNeuronen.size(); i++) {
			ergebnis[i] = outputNeuronen.get(i).out();
			err[i] = m.teaching[i] - ergebnis[i];
		}

		// Delta-Regel
		double lernfaktor = 0.1;
		for (int i = 0; i < outputNeuronen.size(); i++) {
			if (ergebnis[i] != m.teaching[i]) {
				failed = true;
				for (Synapse s : outputNeuronen.get(i).dendriten) {
					s.gewicht = s.gewicht + lernfaktor * s.von.out() * err[i];
				}
			}
		}
		
		// Liefert, ob das Neuron nun korrekt arbeitet
		return failed;
	}

	
	
	// Das Netz lernt nach der Delta-Regel eine Menge von Muster
	public int lernen_all(LinkedList<Muster> muster) {
		boolean failed = true;
		// sollte er laenger brauchen, um dieses Muster zu lernen, ist es
		// wahrscheinlich nicht linear trennbar
		int maxIterationen = 1000;
		int currentIteration = 0;

		while (failed && currentIteration < maxIterationen) {
			failed = false;
			currentIteration++;

			for (Muster m : muster) {
				boolean thisFailed = lernen(m);
				if (thisFailed){
					failed = true;
				}
			}
		}

		// Liefere die Anzahl benoetigter Lernschritte, um das Muster zu
		// erlernen
		
		if (currentIteration > maxIterationen - 5 ){
			System.out.println("FAIL!");
		}
		
		return currentIteration;
	}

	
	
	// Das Netz lernt nach der Delta-Regel eine Menge von Muster
	public int pocket_lernen(LinkedList<Muster> muster) {
		int failes = 1;
		int minFailes = 100000;
		double[][] besteGewichte = new double[inputNeuronen.size()][outputNeuronen.size()];
		
		// sollte er laenger brauchen, um dieses Muster zu lernen, ist es
		// wahrscheinlich nicht linear trennbar
		int maxIterationen = 1000;
		int currentIteration = 0;

		while (failes > 0 && currentIteration < maxIterationen) {
			currentIteration++;
			failes = 0;
			
			// Veraendere die Gewichte fuer alle Muster
			for (Muster m : muster) {
				boolean thisFailed = lernen(m);
				if (thisFailed){
					failes++;
				}
			}

			// War dieser Durchlauf besser als die davor?
			// Wenn ja, dann speichere dir die Gewichte.
			if (failes < minFailes){
				minFailes = failes;
				int iCount = 0;
				int oCount = 0;

				for(Neuron o : outputNeuronen){
					for(Synapse s : o.dendriten){
						besteGewichte[iCount][oCount] = s.gewicht; 
						iCount++;
					}
					iCount=0;
					oCount++;
				}
				
			}
		
		}

		// Nutze die gespeicherten Gewichte, sollte der Algorithmus nicht terminiert sein
		if (currentIteration >= maxIterationen){
			int iCount = 0;
			int oCount = 0;
			for(Neuron o : outputNeuronen){
				for(Synapse s : o.dendriten){
					s.gewicht = besteGewichte[iCount][oCount]; 
					iCount++;
				}
				iCount=0;
				oCount++;
			}
			
		}
		
		
		// Liefere die Anzahl benoetigter Lernschritte, um das Muster zu
		// erlernen
		return currentIteration;
	}
		

	
	// Das Netz liefert das Ergebnis zu einem Muster
	public double[] ergebnisZuMuster(Muster m) {
		// Muster auf Netz uebertragen
		for (int i = 0; i < m.input.length; i++) {
			inputNeuronen.get(i).standard_output = m.input[i];
		}

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
			// Muster auf Netz uebertragen
			for (int i = 0; i < m.input.length; i++) {
				inputNeuronen.get(i).standard_output = m.input[i];
			}

			// Ergebnisse ausrechnen
			double[] ergebnis = new double[outputNeuronen.size()];
			for (int i = 0; i < outputNeuronen.size(); i++) {
				ergebnis[i] = outputNeuronen.get(i).out();
			}

			System.out.println(Arrays.toString(ergebnis));
		}
	}

}
