package sample_code.Java.optional.example;

import java.util.Optional;

public class OrElse_Vs_OrElseGet {

    public static void main(String[] args) {

        Sample sample = new Sample();


        // Optional 객체의 값이 없을 때

//        String name = null;
//
//        System.out.println("orElse()");
//        String defaultName = Optional.ofNullable(name).orElse(sample.getDefaultName());
//
//        System.out.println("OrElseGet()");
//        defaultName = Optional.ofNullable(name).orElseGet(sample::getDefaultName);



        // Optional 객체의 값이 있을 때

        String name = "Alice";

        System.out.println("orElse()");
        String defaultName = Optional.ofNullable(name).orElse(sample.getDefaultName());

        System.out.println("OrElseGet()");
        defaultName = Optional.ofNullable(name).orElseGet(sample::getDefaultName);

    }

}

class Sample {

    public String getDefaultName() {

        System.out.println("getDefaultName() executed...");

        return "Unknown";
    }

}



