package old.sample_code.java_for_framework.java_interface.default_method.function_extension;

public interface Ability {

}

interface Swimmable {
    default void swim() {
        System.out.println("swim()");
    }
}

interface Walkable {
    default void walk() {
        System.out.println("walk()");
    }
}

interface Flyable {
    default void fly() {
        System.out.println("fly()");
    }
}


