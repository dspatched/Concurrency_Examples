
public class ClientThread extends Thread {

	private FactorialsProducer producer;

	public ClientThread(FactorialsProducer factorialsProducer) {
		this.producer = factorialsProducer;
	}
	
	@Override
	public void run() {
		int i = 10;
		long val = producer.getFactorial(10);
		System.out.format("Factorial of %d is %d\n", i, val);
	}

}
