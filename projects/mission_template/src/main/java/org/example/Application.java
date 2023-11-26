package org.example;

import org.example.controller.MainController;
import org.example.view.input.InputExceptionHandler;
import org.example.view.input.InputView;
import org.example.view.input.InputViewImpl;
import org.example.view.input.InputViewProxy;
import org.example.view.output.OutputFormatter;
import org.example.view.output.OutputView;
import org.example.view.print.ConsolePrinter;
import org.example.view.print.Printer;
import org.example.view.read.ConsoleReader;
import org.example.view.read.Reader;

public class Application {

    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        Printer printer = new ConsolePrinter();

        InputView inputView = setUpInputView(printer, reader);
        OutputView outputView = setUpOutputView(printer);

        MainController mainController = new MainController(inputView, outputView);
        mainController.run();
    }

    private static InputView setUpInputView(Printer printer, Reader reader) {
        return new InputViewProxy(new InputExceptionHandler(printer), new InputViewImpl(reader, printer));
    }

    private static OutputView setUpOutputView(Printer printer) {
        return new OutputView(printer, new OutputFormatter());
    }

}
