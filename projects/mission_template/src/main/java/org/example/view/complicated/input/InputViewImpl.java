package org.example.view.complicated.input;


import org.example.utils.StringConvertor;
import org.example.utils.StringValidator;
import org.example.view.complicated.print.Printer;
import org.example.view.complicated.read.Reader;

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
