package org.example.view.complicated.print;

public class ConsolePrinter implements Printer {

    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

    @Override
    public void printEmptyLine() {
        System.out.println();
    }

}
