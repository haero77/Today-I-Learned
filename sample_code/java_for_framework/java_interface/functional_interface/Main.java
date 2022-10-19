package sample_code.java_for_framework.java_interface.functional_interface;

class SayHello implements MyRunnable {
    @Override
    public void run() {
        System.out.println(new Greeting().supply());
    }
}

class Greeting implements MySupply {
    @Override
    public String supply() {
        return "HelloWorld";
    }
}

public class Main {
    public static void main(String[] args) {
/*
        MySupply mySupply3 = new MySupply() {
            @Override
            public String supply() {
                return "Hello World";
            }
        };

        System.out.println(mySupply3.supply());

        MyRunnable myRunnable1 = new MyRunnable() {
            @Override
            public void run() {
                System.out.println("run!");
            }
        };

        MyRunnable myRunnable2 = () -> System.out.println("run!");
        myRunnable2.run();

        MySupply mySupply1 = new MySupply() {
            @Override
            public String supply() {
                return "Hello World!";
            }
        };
        System.out.println(mySupply1.supply());

        MySupply mySupply2 = () -> "Hello World!";
        System.out.println(mySupply2.supply());
*/

        MyRunnable myRunnable = () -> {
            MySupply mySupply = () -> "Bye World!";
            System.out.println(mySupply.supply());
        };
        myRunnable.run(); // "Bye World" 출력
    }
}
