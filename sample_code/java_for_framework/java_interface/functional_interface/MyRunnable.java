package sample_code.java_for_framework.java_interface.functional_interface;

@FunctionalInterface
public interface MyRunnable {
    void run();
}

@FunctionalInterface
interface MyMap {
    void map();

    default void sayHello() {
        System.out.println("Hello World");
    }

    static void sayBye() {
        System.out.println("Bye World");
    }
}
