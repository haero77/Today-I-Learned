package org.example.view.output;

import org.example.view.print.Printer;

public class OutputView {

    private final Printer printer;
    private final OutputFormatter formatter;

    public OutputView(Printer printer, OutputFormatter formatter) {
        this.printer = printer;
        this.formatter = formatter;
    }

    public void showErrorMessageWithReInput(String message) {
        printer.printLine(ReInputMessageFormatter.formatWithErrorPrefix(message));
    }

}
