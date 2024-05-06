package org.example.ch04_composition_is_better_than_extends;

import java.util.ArrayList;

public class Container extends ArrayList<Luggage> {

    private int maxSize;
    private int currentSize;

    public Container(int maxSize) {
        this.maxSize = maxSize;
    }

    public void put(Luggage luggage) throws NotEnougthSpaceException {
        if (!canContain(luggage)) {
            throw new NotEnougthSpaceException();
        }
        super.add(luggage);
        currentSize += luggage.size();
    }

    public void extract(Luggage luggage) {
        super.remove(luggage);
        this.currentSize -= luggage.size();
    }

    public boolean canContain(Luggage luggage) {
        return currentSize + luggage.size() <= maxSize;
    }

}
