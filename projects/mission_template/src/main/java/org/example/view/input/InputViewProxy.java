package org.example.view.input;

public class InputViewProxy implements InputView {

    private final InputExceptionHandler exceptionHandler;
    private final InputViewImpl inputView;

    public InputViewProxy(InputExceptionHandler exceptionHandler, InputViewImpl inputView) {
        this.exceptionHandler = exceptionHandler;
        this.inputView = inputView;
    }

    //    @Override
//    public int inputVisitDate() {
//        return exceptionHandler.reInput(
//                view::inputVisitDate,
//                ReInputMessageFormatter.formatWithErrorPrefix(DomainErrorMessage.VISIT_DATE_ERROR)
//        );
//    }
//
//    @Override
//    public OrderRequest inputOrderRequest() {
//        return exceptionHandler.reInput(
//                view::inputOrderRequest,
//                ReInputMessageFormatter.formatWithErrorPrefix(DomainErrorMessage.ORDER_ERROR)
//        );
//    }

}