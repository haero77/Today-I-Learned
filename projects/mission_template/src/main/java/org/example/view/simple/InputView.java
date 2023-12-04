package org.example.view.simple;

import camp.nextstep.edu.missionutils.Console;
import org.example.view.complicated.read.ConsoleReader;

public class InputView {

    private String readLine() {
        return Console.readLine().trim();
    }

    private void println(String message) {
        System.out.println(message);
    }

}
