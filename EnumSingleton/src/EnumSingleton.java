
public enum EnumSingleton {

	INSTANCE;
	
	private int value = 0;
	
	private EnumSingleton() {
		System.out.format("Constructor called value=%d\n", ++value);
		if(value == 2) throw new RuntimeException("BOOM");
	}

	public void doSomething() {
		System.out.println("Did something");
	}

}
