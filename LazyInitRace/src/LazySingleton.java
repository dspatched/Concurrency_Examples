
public class LazySingleton {

	private static LazySingleton instance;
	
	public static LazySingleton getInstance() {
		if(instance == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance = new LazySingleton();
		}
		return instance;
	}
	
	private LazySingleton() {
		System.out.format("Constructor called\n");
	}

}
