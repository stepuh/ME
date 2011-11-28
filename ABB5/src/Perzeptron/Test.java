package Perzeptron;

import java.util.LinkedList;

public class Test {

	public static void main(String[] args){
	
//		PerzeptronNetz netz = new PerzeptronNetz(3,8);
//
//		LinkedList<Muster> muster = new LinkedList<Muster>();
//		double[] i0 = {0,0,0}; double[] o0= {0,0,0,0,0,0,0,0}; Muster m_0 = new Muster(i0,o0); muster.add(m_0);
//		double[] i1 = {0,0,1}; double[] o1= {1,0,0,0,0,0,0,0}; Muster m_1 = new Muster(i1,o1); muster.add(m_1);
//		double[] i2 = {0,1,0}; double[] o2= {1,1,0,0,0,0,0,0}; Muster m_2 = new Muster(i2,o2); muster.add(m_2);
//		double[] i3 = {0,1,1}; double[] o3= {1,1,1,0,0,0,0,0}; Muster m_3 = new Muster(i3,o3); muster.add(m_3);
//		double[] i4 = {1,0,0}; double[] o4= {1,1,1,1,0,0,0,0}; Muster m_4 = new Muster(i4,o4); muster.add(m_4);
//		double[] i5 = {1,0,1}; double[] o5= {1,1,1,1,1,0,0,0}; Muster m_5 = new Muster(i5,o5); muster.add(m_5);
//		double[] i6 = {1,1,0}; double[] o6= {1,1,1,1,1,1,0,0}; Muster m_6 = new Muster(i6,o6); muster.add(m_6);
//		double[] i7 = {1,1,1}; double[] o7= {1,1,11,1,1,1,0}; Muster m_7 = new Muster(i7,o7); muster.add(m_7);

		PerzeptronNetz netz = new PerzeptronNetz(2,1);
		LinkedList<Muster> muster = new LinkedList<Muster>();
		double[] i0 = {0,0}; double[] o0= {0}; Muster m_0 = new Muster(i0,o0); muster.add(m_0);
		double[] i1 = {0,1}; double[] o1= {0}; Muster m_1 = new Muster(i1,o1); muster.add(m_1);
		double[] i2 = {1,0}; double[] o2= {0}; Muster m_2 = new Muster(i2,o2); muster.add(m_2);
		double[] i3 = {1,1}; double[] o3= {1}; Muster m_3 = new Muster(i3,o3); muster.add(m_3);
	
		
				
		System.out.println("nicht gelernt:");
		netz.ergebnisZuMuster_all(muster);
		
		System.out.println("lernen...");
		netz.lernen_all(muster);
		
		
		System.out.println("gelernt! UND ... 00, 01, 10, 11:");
		netz.ergebnisZuMuster_all(muster);
	}

	
	
	public static void arrayOut(double[] a){
		if(a.length == 0){
			System.out.println("[]");
		}else if(a.length == 1){
			System.out.println("["+a[0]+"]");
		}else{
			System.out.print("[");
			for(int i=0; i<a.length-1; i++){
				System.out.print(a[i]+", ");
			}
			System.out.println(a[a.length-1]+"]");
		}
	}
	
}



