/*
 * This examples demonstrates that stateless objects are thread-safe
 */
public class StatelessFactorial {
	
	public static void main(String[] args) throws InterruptedException {
		FactorialsProducer factorialsProducer = new FactorialsProducer();
		ClientThread t1 = new ClientThread(factorialsProducer);
		ClientThread t2 = new ClientThread(factorialsProducer);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

}
