package org.example.utils;

public class StringConvertor {

    private StringConvertor() {
    }

    public static int toInt(String source) {
        NumericValidator.validateNumeric(source);
        return Integer.parseInt(source);
    }
    
}
