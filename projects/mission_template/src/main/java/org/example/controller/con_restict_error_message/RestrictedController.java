package org.example.controller.con_restict_error_message;

import java.util.function.Supplier;
import org.example.domain.BridgeSize;
import org.example.domain.ErrorMessage;
import org.example.view.input.InputView;
import org.example.view.output.OutputView;

public class RestrictedController {

    private final InputView inputView;
    private final OutputView outputView;

    public RestrictedController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        welcomePlayer();

        BridgeSize bridgeSize = inputBridgeSize();
    }

    private void welcomePlayer() {
        outputView.welcomePlayer();
    }

    private BridgeSize inputBridgeSize() {
        return input(() -> BridgeSize.sizeOf(inputView.inputBridgeSize()), ErrorMessage.BRIDGE_ERROR);
    }

    private <T> T input(Supplier<T> supplier, String message) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            outputView.showErrorMessage(message);
            return input(supplier, message);
        }
    }

    // 도메인 로직에서 에러나는 경우 처리i
    private <T> T process(Supplier<T> supplier, ErrorConsumer consumer) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            consumer.accept();
            return process(supplier, consumer);
        }
    }

}
