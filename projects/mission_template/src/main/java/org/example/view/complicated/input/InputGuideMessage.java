package org.example.view.complicated.input;

public enum InputGuideMessage {

    SAMPLE_GUIDE("입력해주세요");

    private final String message;

    InputGuideMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

