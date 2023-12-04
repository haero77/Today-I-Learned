package mock;


import org.example.view.complicated.read.Reader;

public class FakeReader implements Reader {

    private final String input;

    public FakeReader(String input) {
        this.input = input;
    }

    @Override
    public String readLine() {
        return this.input;
    }

}
