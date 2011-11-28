package src;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Client18 {
	
	public static double[] getUnary(int zahl){
		double[][] zahlenUnary = new double[10][10];
		double[] zahl0 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
		double[] zahl1 = {0, 0, 0, 0, 0, 0, 0, 0, 1, 0};
		double[] zahl2 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
		double[] zahl3 = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
		double[] zahl4 = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
		double[] zahl5 = {0, 0, 0, 0, 1, 0, 0, 0, 0, 0};
		double[] zahl6 = {0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
		double[] zahl7 = {0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
		double[] zahl8 = {0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
		double[] zahl9 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		zahlenUnary[0] = zahl0;
		zahlenUnary[1] = zahl1;
		zahlenUnary[2] = zahl2;
		zahlenUnary[3] = zahl3;
		zahlenUnary[4] = zahl4;
		zahlenUnary[5] = zahl5;
		zahlenUnary[6] = zahl6;
		zahlenUnary[7] = zahl7;
		zahlenUnary[8] = zahl8;
		zahlenUnary[9] = zahl9;
		return zahlenUnary[zahl];
	}

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Dataset> training = new Reader("training.txt").getDatasets();
		PerzeptronNetz netz = new PerzeptronNetz(16,10);
		LinkedList<Muster> muster = new LinkedList<Muster>();
		
		for(Dataset d: training){
			muster.add(new Muster( d.features, getUnary(d.correctKlass)));
		}
		
		netz.lernen_all(muster);
	}

}
