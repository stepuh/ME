package Perzeptron;

import java.util.LinkedList;

public class Neuron {

	// Attribute
	public LinkedList<Synapse> dendriten = new LinkedList<Synapse>();	// Input - Synapsen 
	public double standard_output;
	public double bias = 0.5;											// Schwellenwert

	
	
	// Erstellt eine neue Synapse mit einem anderen Neuron mit einer bestimmten Wertigkeit
	public void erstelleSynapse(Neuron ziel, double gewicht){
		Synapse s = new Synapse(this, ziel, gewicht);
		ziel.dendriten.add(s);
	}

	
	// Propagierfunktion
	public double net(){
		// keine Dendriten -> Eingabeschicht
		if (dendriten.size() == 0){
			return standard_output;
		}else{
			double wert = 0.0;		
			for(Synapse s : dendriten){
				wert = wert + s.von.out() * s.gewicht;
			}
//			System.out.println(wert);
			return wert;
			
		}
	}
	
	
	// Aktivitaetsfunktion
	public double act(){
		if (net() - bias >= 0){
			return 1;
		}else{
			return 0;
		}
	} 
	
	
	// Ausgabefunktion
	public double out(){
		return act();
	}
	
}
