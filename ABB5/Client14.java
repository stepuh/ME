
public class Client14 {
	
	public static void main(String[] args) {
		
		System.out.println("AND:");
		// AND
		Neuron inAnd0 = new Neuron();
		Neuron inAnd1 = new Neuron();
		Neuron outAnd0 = new Neuron();
		
		// verbinden
		inAnd0.erstelleSynapse(outAnd0, 1);
		inAnd1.erstelleSynapse(outAnd0, 1);
		
		// bias setzen
		outAnd0.bias = 2;
		
		// 0 0 
		inAnd0.standard_output = 0;
		inAnd1.standard_output = 0;
		System.out.println("0 0 -> "+outAnd0.out());
		// 0 1
		inAnd0.standard_output = 0;
		inAnd1.standard_output = 1;
		System.out.println("0 1 -> "+outAnd0.out());
		// 1 0 
		inAnd0.standard_output = 1;
		inAnd1.standard_output = 0;
		System.out.println("1 0 -> "+outAnd0.out());
		// 1 1 
		inAnd0.standard_output = 1;
		inAnd1.standard_output = 1;
		System.out.println("1 1 -> "+outAnd0.out());
		
		
		System.out.println("OR:");
		// OR
		Neuron inOr0 = new Neuron();
		Neuron inOr1 = new Neuron();
		Neuron outOr0 = new Neuron();
		
		// verbinden
		inOr0.erstelleSynapse(outOr0, 1);
		inOr1.erstelleSynapse(outOr0, 1);
		
		// bias setzen
		outOr0.bias = 1;
		
		// 0 0 
		inOr0.standard_output = 0;
		inOr1.standard_output = 0;
		System.out.println("0 0 -> "+outOr0.out());
		// 0 1
		inOr0.standard_output = 0;
		inOr1.standard_output = 1;
		System.out.println("0 1 -> "+outOr0.out());
		// 1 0 
		inOr0.standard_output = 1;
		inOr1.standard_output = 0;
		System.out.println("1 0 -> "+outOr0.out());
		// 1 1 
		inOr0.standard_output = 1;
		inOr1.standard_output = 1;
		System.out.println("1 1 -> "+outOr0.out());

	}

}
