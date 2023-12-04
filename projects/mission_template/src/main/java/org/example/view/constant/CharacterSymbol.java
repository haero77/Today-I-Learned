package org.example.view.constant;

public enum CharacterSymbol {

    HYPHEN("-"),
    BLANK(" "),
    COMMA(","),
    COLON(":"),
    NEW_LINE(System.lineSeparator()),
    EMPTY("");

    private final String literal;

    CharacterSymbol(String symbolLiteral) {
        this.literal = symbolLiteral;
    }

    public String getLiteral() {
        return literal;
    }

}
