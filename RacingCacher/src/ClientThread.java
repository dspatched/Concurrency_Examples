
public class ClientThread extends Thread {
	
	private FactorialsProducer producer;

	public ClientThread(FactorialsProducer producer, String name) {
		super(name);
		this.producer = producer;
	}

	@Override
	public void run() {
		int i = 10;
		long val = producer.getFactorial(10);
		System.out.format("%s Factorial of %d is %d\n", getName(), i, val);
	}
}
