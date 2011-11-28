package Perzeptron;

import java.util.ArrayList;
import java.util.LinkedList;

public class PerzeptronNetz {

	// Attribute
	public LinkedList<Neuron> inputNeuronen = new LinkedList<Neuron>();
	public LinkedList<Neuron> outputNeuronen = new LinkedList<Neuron>();

	
	
	// Konstruktor
	public PerzeptronNetz(int in, int out){
		// Input-Neuronen erstellen
		for(int i = 0; i<in; i++){
			Neuron n = new Neuron();
			inputNeuronen.add(n);
		}
		
		// Output-Neuronen erstellen
		for(int i = 0; i<out; i++){
			Neuron n = new Neuron();
			outputNeuronen.add(n);
		}

		// Neuronen vernetzen
		for(Neuron input : inputNeuronen){
			for(Neuron output : outputNeuronen){
				input.erstelleSynapse(output, 0.0);
			}
		}
	}
	
	
	
	// Das Netz lernt nach der Delta-Regel
	public void lernen(Muster m){
		// Muster auf Netz uebertragen
		for(int i = 0; i < m.input.length; i++){
			inputNeuronen.get(i).standard_output = m.input[i];
		}

		// Ergebnisse und Error-Werte ausrechnen
		double[] ergebnis = new double[outputNeuronen.size()];
		double[] err = new double[outputNeuronen.size()];
		for(int i=0; i<outputNeuronen.size(); i++){
			ergebnis[i] = outputNeuronen.get(i).out();
			err[i] = m.teaching[i] - ergebnis[i];
		}
		
		// Delta-Regel
		double lernfaktor = 0.2;
		for(int i=0; i<outputNeuronen.size(); i++){
			if (ergebnis[i] != m.teaching[i]){
				for (Synapse s : outputNeuronen.get(i).dendriten){
					s.gewicht = s.gewicht + lernfaktor * s.von.out() * err[i];
				}
			}
		}
		
	}
	
	
	
	// Das Netz lernt nach der Delta-Regel
	public void lernen_all(LinkedList<Muster> muster){
		double lernfaktor = 0.2;
		boolean failed = true;
		
		while(failed){
			failed = false;
			for(Muster m : muster){
				// Muster auf Netz uebertragen
				for(int i = 0; i < m.input.length; i++){
					inputNeuronen.get(i).standard_output = m.input[i];
				}
		
				// Ergebnisse und Error-Werte ausrechnen - ggf. Gewichte anpassen
				double[] ergebnis = new double[outputNeuronen.size()];
				double[] err = new double[outputNeuronen.size()];
				for(int i=0; i<outputNeuronen.size(); i++){
					ergebnis[i] = outputNeuronen.get(i).out();
					err[i] = m.teaching[i] - ergebnis[i];

					if (ergebnis[i] != m.teaching[i]){
						failed = true;
						for (Synapse s : outputNeuronen.get(i).dendriten){
							s.gewicht = s.gewicht + lernfaktor * s.von.out() * err[i];
						}
					}
				}

			}//for
		}//while
	}
	
	
	
	// Das Netz liefert das Ergebnis zu einem Muster
	public double[] ergebnisZuMuster(Muster m){
		// Muster auf Netz uebertragen
		for(int i = 0; i < m.input.length; i++){
			inputNeuronen.get(i).standard_output = m.input[i];
		}
		
		// Ergebnisse ausrechnen
		double[] ergebnis = new double[outputNeuronen.size()];
		for(int i=0; i<outputNeuronen.size(); i++){
			ergebnis[i] = outputNeuronen.get(i).out();
		}
		
		return ergebnis;
	}
	
	
	// Das Netz liefert das Ergebnis zu einem Muster
	public void ergebnisZuMuster_all(LinkedList<Muster> muster){
		for (Muster m : muster){
			// Muster auf Netz uebertragen
			for(int i = 0; i < m.input.length; i++){
				inputNeuronen.get(i).standard_output = m.input[i];
			}
			
			// Ergebnisse ausrechnen
			double[] ergebnis = new double[outputNeuronen.size()];
			for(int i=0; i<outputNeuronen.size(); i++){
				ergebnis[i] = outputNeuronen.get(i).out();
			}
		
			Test.arrayOut(ergebnis);
		}
	}
	
}
