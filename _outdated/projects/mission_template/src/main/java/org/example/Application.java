package org.example;

import org.example.controller.con_restict_error_message.RestrictedController;
import org.example.view.complicated.input.InputExceptionHandler;
import org.example.view.complicated.input.InputView;
import org.example.view.complicated.input.InputViewImpl;
import org.example.view.complicated.input.InputViewProxy;
import org.example.view.complicated.output.OutputView;
import org.example.view.complicated.print.ConsolePrinter;
import org.example.view.complicated.print.Printer;
import org.example.view.complicated.read.ConsoleReader;
import org.example.view.complicated.read.Reader;

public class Application {

    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        Printer printer = new ConsolePrinter();

        InputView inputView = setUpInputView(printer, reader);
        OutputView outputView = new OutputView(printer);

//        MainController mainController = new MainController(inputView, outputView);
//        mainController.run();

//        NonRestrictedController controller =
//                new NonRestrictedController(new InputViewImpl(reader, printer), new OutputView(printer));

        RestrictedController controller = new RestrictedController(new InputViewImpl(reader, printer), outputView);

        controller.run();
    }

    private static InputView setUpInputView(Printer printer, Reader reader) {
        return new InputViewProxy(new InputExceptionHandler(printer), new InputViewImpl(reader, printer));
    }

}
