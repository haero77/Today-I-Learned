package org.example.controller;

@FunctionalInterface
public interface ErrorConsumer {

    void accept() throws IllegalArgumentException, IllegalStateException;

}
