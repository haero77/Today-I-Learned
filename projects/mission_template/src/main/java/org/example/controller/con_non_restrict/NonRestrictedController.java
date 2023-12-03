package org.example.controller.con_non_restrict;

import java.util.function.Supplier;
import org.example.domain.BridgeSize;
import org.example.view.input.InputView;
import org.example.view.output.OutputView;

public class NonRestrictedController {

    private final InputView inputView;
    private final OutputView outputView;

    public NonRestrictedController(InputView inputView, OutputView outputView) {
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
        return input(() -> BridgeSize.sizeOf(inputView.inputBridgeSize()));
    }

    private <T> T input(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.showErrorMessage(e.getMessage());
            return input(supplier);
        }
    }

}
