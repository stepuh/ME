

import java.util.LinkedList;

public class Neuron {

	// Attribute
	public LinkedList<Synapse> inputSynapsen = new LinkedList<Synapse>(); // Input- Synapsen
	public LinkedList<Synapse> outputSynapsen = new LinkedList<Synapse>(); // Output- Synapsen
	
	public boolean istKonstant = false;	// true, sollte es sich um ein Input-Neuron, oder um das Bias-Neuron handeln
	public double standard_output;		
	
	public double back_speicher;				// Zwischenspeicher für den Backpropagation-Algorithmus
	public double ableitung_speicher;			// Zwischenspeicher für den Forward-Schritt
	
	
	// Erstellt eine neue Synapse mit einem anderen Neuron mit einer bestimmten
	// Wertigkeit
	public void erstelleSynapse(Neuron ziel, double gewicht) {
		Synapse s = new Synapse(this, ziel, gewicht);
		ziel.inputSynapsen.add(s);
		this.outputSynapsen.add(s);
	}


	
	// Aktivierungsfunktion -> nutzt sigmuide Funktion
	public double act(){
		double k = 1.0;
		double exponent = 0.0;
		for (Synapse s : inputSynapsen) {
			exponent = exponent + s.von.out() * s.gewicht;
		}
			
		return 1.0 / (1.0 + Math.exp(-k * exponent));
	}

	
	
	public void forward(){
		double tmp  = act();
		ableitung_speicher = tmp * (1 - tmp);
	}
	
	
	public void backward(double input){
		for(Synapse s : inputSynapsen){
			back_speicher = ableitung_speicher * input;
			s.von.backward(back_speicher);
		}
	}
	
	
	
	// Output berechnen
	public double out() {
		if (istKonstant){
			return standard_output;
		}else{
			if (act() > 0){
				return 1;
			}else{
				return 0;
			}
		}
	}

}
