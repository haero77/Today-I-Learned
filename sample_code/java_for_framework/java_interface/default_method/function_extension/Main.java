package sample_code.java_for_framework.java_interface.default_method.function_extension;

class Duck implements Swimmable, Walkable {}

class Swan implements Swimmable, Walkable, Flyable {}

public class Main {
    public static void main(String[] args) {
        new Duck().swim();
        new Swan().fly();
    }
}
