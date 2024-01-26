package modern_java_in_action.ch3_lambda;

public class Main {

	public static void main(String[] args) {
		Runnable r = () -> {
			System.out.println("something");
		};

		run(r);
	}

	static void run(Runnable runnable) {
		runnable.run();
	}

}
