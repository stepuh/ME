package src;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Netz {

	public LinkedList<Neuron> inputNeuronen = new LinkedList<Neuron>();
	public LinkedList<Neuron> outputNeuronen = new LinkedList<Neuron>();

	// Konstruktor
	public Netz(int in, int out) {
		// Input-Neuronen erstellen
		for (int i = 0; i < in+1; i++) {
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
				input.erstelleSynapse(output, Math.random());
			}
		}
		inputNeuronen.getLast().standard_output = -1;
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
		double lernfaktor = 0.01;
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
		int maxIterationen = 1000000;
		int currentIteration = 0;

		while (failed && currentIteration < maxIterationen) {
			failed = false;
			currentIteration++;

			for (Muster m : muster) {
				failed = lernen(m);
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
