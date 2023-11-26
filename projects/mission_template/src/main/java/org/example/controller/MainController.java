package org.example.controller;

import java.util.function.Supplier;
import org.example.view.input.InputView;
import org.example.view.output.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

    }

    private <T> T process(Supplier<T> supplier, ErrorConsumer consumer) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            consumer.accept();
            return process(supplier, consumer);
        }
    }

//    private VisitDate inputVisitDate() {
//        return process(
//                () -> VisitDate.from(inputView.inputVisitDate()),
//                () -> outputView.showErrorMessageWithReInput(DomainErrorMessage.VISIT_DATE_ERROR)
//        );
//    }
//
//    private Order inputOrder() {
//        return process(
//                this::processOrder,
//                () -> outputView.showErrorMessageWithReInput(DomainErrorMessage.ORDER_ERROR)
//        );
//    }

}
