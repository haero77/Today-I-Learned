package org.example.domain;

public class BridgeSize {

    private final int size;

    private BridgeSize(int size) {
        this.size = size;
    }

    public static BridgeSize sizeOf(int size) {
        validateSize(size);
        return new BridgeSize(size);
    }

    private static void validateSize(int size) {
        if (meetsSizeCriterion(size)) {
            throw new IllegalArgumentException("size not acceptable.");
        }
    }

    private static boolean meetsSizeCriterion(int size) {
        return size >= 1;
    }

}
