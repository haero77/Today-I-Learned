package org.example.view.input;


import org.example.common.utils.StringConvertor;
import org.example.common.utils.validator.StringValidator;
import org.example.view.print.Printer;
import org.example.view.read.Reader;

public class InputViewImpl implements InputView {

    private final Reader reader;
    private final Printer printer;

    public InputViewImpl(Reader reader, Printer printer) {
        this.reader = reader;
        this.printer = printer;
    }

    @Override
    public int inputBridgeSize() {
        printer.printLine(InputGuideMessage.SAMPLE_GUIDE.getMessage());
        String rawInput = reader.readLine();

        StringValidator.validateHasText(rawInput);

        return StringConvertor.toInt(rawInput);
    }

}
