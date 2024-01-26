package old.sample_code.Java.optional.example;

import java.util.Optional;

public class OrElse_OrElseGet_Ex {

    public static void main(String[] args) {

        // orElse()
        String name1 = "BaekDoong";
        Optional<String> optName1 = Optional.ofNullable(name1);
        System.out.println(optName1.orElse("Unknown")); // BaekDoong

        String name2 = null;
        Optional<String> optName2 = Optional.ofNullable(name2);
        System.out.println(optName2.orElse("Unknown")); // Unknown

        // orElseGet()
//        String name1 = "BaekDoong";
//        Optional<String> optName1 = Optional.ofNullable(name1);
//        System.out.println(optName1.orElseGet(() -> "Unknown"));
//
//        String name2 = null;
//        Optional<String> optName2 = Optional.ofNullable(name2);
//        System.out.println(optName2.orElseGet(() -> "Unknown"));
    }
}
