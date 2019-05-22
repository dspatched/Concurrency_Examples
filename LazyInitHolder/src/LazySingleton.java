
public class LazySingleton {

	private static LazySingleton instance;
	
	private static volatile int value = 0;
	
	private static class InstanceHolder {
		private final static LazySingleton INSTANCE = new LazySingleton();  
	}

	private LazySingleton() {
		System.out.format("Constructor called value=%d\n", ++value);
		if(value == 2) throw new RuntimeException("BOOM");
	}

	public static LazySingleton getInstance() {
		return InstanceHolder.INSTANCE;
	}

}
