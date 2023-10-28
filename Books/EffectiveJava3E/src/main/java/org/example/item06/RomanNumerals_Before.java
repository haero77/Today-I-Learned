package org.example.item06;

public class RomanNumerals_Before {

    static boolean isRomanNumeral(String s){
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" +
                "(X[CL]|L?X{0,3})(I[XY]|V?I{0,3})$");
    }

}
