public class Livelock {
	static class Computer {
		private Programmer owner;

		public Computer(Programmer d) {
			owner = d;
		}

		public Programmer getProgrammer() {
			return owner;
		}

		public synchronized void setOwner(Programmer d) {
			owner = d;
		}

		public synchronized void use() {
			System.out.printf("%s is coding!", owner.name);
		}
	}

	static class Programmer {
		private String name;
		private boolean wantsToCode;

		public Programmer(String n) {
			name = n;
			wantsToCode = true;
		}

		public String getName() {
			return name;
		}

		public boolean mustCode() {
			return wantsToCode;
		}

		public void code(Computer computer, Programmer programmer) {
			while (wantsToCode) {
				if (computer.owner != this) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						continue;
					}
					continue;
				}

				if (programmer.mustCode()) {
					System.out.printf("%s: Well, %s has more important tasks to code%n", name, programmer.getName());
					computer.setOwner(programmer);
					continue;
				}

				computer.use();
				wantsToCode = false;
				System.out.printf("%s: I am coding %s!%n", name, programmer.getName());
				computer.setOwner(programmer);
			}
		}
	}

	public static void main(String[] args) {
		final Programmer p1 = new Programmer("Vanya");
		final Programmer p2 = new Programmer("Sanya");

		final Computer s = new Computer(p1);

		new Thread(new Runnable() {
			public void run() {
				p1.code(s, p2);
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				p2.code(s, p1);
			}
		}).start();
	}
}