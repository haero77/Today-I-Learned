package org.example.common.utils;

import org.example.common.utils.validator.NumericValidator;

public class StringConvertor {

    private StringConvertor() {
    }

    public static int toInt(String source) {
        NumericValidator.validateNumeric(source);
        return Integer.parseInt(source);
    }
    
}
