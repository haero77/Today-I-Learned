package org.example.view.utils;

import org.example.view.constant.CharacterSymbol;

public class ReInputMessageFormatter {

    private static final String RE_INPUT = "다시 입력해 주세요.";

    private ReInputMessageFormatter() {
    }

    public static String formatWithErrorPrefix(String message) {
        return ErrorMessageFormatter.addErrorPrefix(message)
                + CharacterSymbol.BLANK.getLiteral()
                + RE_INPUT;
    }

}
