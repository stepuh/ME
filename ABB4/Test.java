
public class Test extends AbstractEM{
	
	int rounds;
	
	Test(){
		rounds = 0;
	}
	@Override
	void expectation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void maximization() {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean condition() {
		System.out.println(rounds++);
		return rounds < 10;
	}

	@Override
	void identifyPrototypes() {
		// TODO Auto-generated method stub
		
	}

}
