package org.example.controller.con_restict_error_message;

@FunctionalInterface
public interface ErrorConsumer {

    void accept() throws IllegalArgumentException, IllegalStateException;

}
