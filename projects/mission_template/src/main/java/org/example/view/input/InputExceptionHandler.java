package org.example.view.input;

import java.util.function.Supplier;
import org.example.view.print.Printer;

public class InputExceptionHandler {

    private final Printer printer;

    public InputExceptionHandler(Printer printer) {
        this.printer = printer;
    }

    public <T> T reInput(Supplier<T> supplier, String errorMessage) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            printer.printLine(errorMessage);
            return reInput(supplier, errorMessage);
        }
    }

}
