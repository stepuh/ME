
public class Synapse {

	// Attribute
	public Neuron von;
	public Neuron zu;
	public double gewicht;

	// Konstruktor
	public Synapse(Neuron von, Neuron zu, double gewicht){
		this.von = von;
		this.zu = zu;
		this.gewicht = gewicht;
	}
	
}
