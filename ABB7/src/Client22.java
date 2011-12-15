


public class Client22 {

	public static void main(String[] args) {

		// Eingabeschicht hat Höhe 2 (+1)
		// 1 Innere Schicht
		// Innere Schichten haben Höhe 2 (+1)
		// Ausgabeschicht hat Höhe 1
		Netz xorNetz = new Netz(3,1,3,1); 
		
		// Setzen nun die Gewichte manuell:
		Neuron n1 = xorNetz.inputNeuronen.get(0);
		n1.outputSynapsen.get(0).gewicht = -4.081;
		n1.outputSynapsen.get(1).gewicht = 4.111;
		n1.outputSynapsen.get(2).gewicht = -2.439;
		
		Neuron n2 = xorNetz.inputNeuronen.get(1);
		n2.outputSynapsen.get(0).gewicht = 6.034;
		n2.outputSynapsen.get(1).gewicht = -5.844;
		n2.outputSynapsen.get(2).gewicht = -3.297;
		
		Neuron n3 = xorNetz.inputNeuronen.get(2);
		n2.outputSynapsen.get(0).gewicht = 3.688;
		n2.outputSynapsen.get(1).gewicht = -3.131;
		n2.outputSynapsen.get(2).gewicht = -1.810;

		
		

	}

}
