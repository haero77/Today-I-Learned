package org.example.view.complicated.output;

import org.example.view.utils.ErrorMessageFormatter;
import org.example.view.complicated.print.Printer;
import org.example.view.utils.ReInputMessageFormatter;

public class OutputView {

    public static final String WELCOME_MESSAGE = "WELCOME MESSAGE";
    private final Printer printer;

    public OutputView(Printer printer) {
        this.printer = printer;
    }

    public void showErrorMessageWithReInput(String message) {
        printer.printLine(ReInputMessageFormatter.formatWithErrorPrefix(message));
    }

    public void welcomePlayer() {
        printer.printLine(WELCOME_MESSAGE);
    }

    public void showErrorMessage(String message) {
        printer.printLine(ErrorMessageFormatter.addErrorPrefix(message));
    }

}
