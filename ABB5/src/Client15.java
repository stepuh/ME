package src;

import java.util.LinkedList;

public class Client15 {

	public static void main(String[] args) {

		// Erstelle Neuronales Netz für AND
		PerzeptronNetz netzAND = new PerzeptronNetz(2, 1);
		LinkedList<Muster> musterAND = new LinkedList<Muster>();
		double[] i00 = { 0, 0 };
		double[] o00 = { 0 };
		Muster m_00 = new Muster(i00, o00);
		musterAND.add(m_00);
		double[] i01 = { 0, 1 };
		double[] o01 = { 0 };
		Muster m_01 = new Muster(i01, o01);
		musterAND.add(m_01);
		double[] i02 = { 1, 0 };
		double[] o02 = { 0 };
		Muster m_02 = new Muster(i02, o02);
		musterAND.add(m_02);
		double[] i03 = { 1, 1 };
		double[] o03 = { 1 };
		Muster m_03 = new Muster(i03, o03);
		musterAND.add(m_03);

		// Erstelle Neuronales Netz für OR
		PerzeptronNetz netzOR = new PerzeptronNetz(2, 1);
		LinkedList<Muster> musterOR = new LinkedList<Muster>();
		double[] i10 = { 0, 0 };
		double[] o10 = { 0 };
		Muster m_10 = new Muster(i10, o10);
		musterOR.add(m_10);
		double[] i11 = { 0, 1 };
		double[] o11 = { 1 };
		Muster m_11 = new Muster(i11, o11);
		musterOR.add(m_11);
		double[] i12 = { 1, 0 };
		double[] o12 = { 1 };
		Muster m_12 = new Muster(i12, o12);
		musterOR.add(m_12);
		double[] i13 = { 1, 1 };
		double[] o13 = { 1 };
		Muster m_13 = new Muster(i13, o13);
		musterOR.add(m_13);

		System.out.println("AND:");
		System.out
				.println("Noch nicht gelernt. Neuron schießt folgende Outputs zu diesen Inputs: 00, 01, 10, 11:");
		netzAND.ergebnisZuMuster_all(musterAND);

		System.out.println("lerne AND...");
		int itAND = netzAND.lernen_all(musterAND);

		double w0 = netzAND.outputNeuronen.get(0).dendriten.get(0).gewicht;
		double w1 = netzAND.outputNeuronen.get(0).dendriten.get(1).gewicht;
		double bias = netzAND.outputNeuronen.get(0).bias;

		System.out.println("AND gelernt in " + itAND + " Lernschritten.");
		System.out.println("Habe Gewichte: w0 = " + w0 + ", w1 = " + w1
				+ ", bias = " + bias);
		System.out
				.println("Neuron schießt folgende Outputs zu diesen Inputs: 00, 01, 10, 11:");
		netzAND.ergebnisZuMuster_all(musterAND);

		System.out.println();

		System.out.println("OR:");
		System.out
				.println("Noch nicht gelernt. Neuron schießt folgende Outputs zu diesen Inputs: 00, 01, 10, 11:");
		netzOR.ergebnisZuMuster_all(musterOR);

		System.out.println("lerne OR...");
		int itOR = netzOR.lernen_all(musterOR);

		w0 = netzOR.outputNeuronen.get(0).dendriten.get(0).gewicht;
		w1 = netzOR.outputNeuronen.get(0).dendriten.get(1).gewicht;
		bias = netzOR.outputNeuronen.get(0).bias;

		System.out.println("OR gelernt in " + itOR + " Lernschritten.");
		System.out.println("Habe Gewichte: w0 = " + w0 + ", w1 = " + w1
				+ ", bias = " + bias);
		System.out
				.println("Neuron schießt folgende Outputs zu diesen Inputs: 00, 01, 10, 11:");
		netzOR.ergebnisZuMuster_all(musterOR);
	}

}
