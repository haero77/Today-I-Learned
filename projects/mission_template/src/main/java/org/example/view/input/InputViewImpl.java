package org.example.view.input;


import org.example.view.print.Printer;
import org.example.view.read.Reader;

public class InputViewImpl implements InputView {

    private final Reader reader;
    private final Printer printer;

    public InputViewImpl(Reader reader, Printer printer) {
        this.reader = reader;
        this.printer = printer;
    }

}
