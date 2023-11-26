package org.example.common.utils;

import java.util.Arrays;
import java.util.List;
import org.example.common.constant.CharacterSymbol;

public class StringUtils {

    private StringUtils() {
    }

    public static String removeBlank(String input) {
        return input.replaceAll(CharacterSymbol.BLANK.getLiteral(), "");
    }

    public static List<String> splitByComma(String input) {
        return Arrays.asList(removeBlank(input).split(CharacterSymbol.COMMA.getLiteral()));
    }

    public static List<String> splitByRegex(String input, String regex) {
        return Arrays.asList(input.split(regex));
    }

}
