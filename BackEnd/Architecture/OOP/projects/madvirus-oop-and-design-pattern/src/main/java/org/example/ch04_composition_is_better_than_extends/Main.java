package org.example.ch04_composition_is_better_than_extends;

public class Main {

    public static void main(String[] args) throws NotEnougthSpaceException {
        final Luggage size3Lug = new Luggage(3);
        final Luggage size2Lug = new Luggage(2);
        final Luggage size1Lug = new Luggage(1);

        final Container container = new Container(5);
        if (container.canContain(size3Lug)) {
            container.put(size3Lug); // 정상 사용
        }
        if (container.canContain(size2Lug)) {
            container.add(size2Lug); // 비정상 사용. Container 사이즈 여분 2에서 줄어들지 않는다.
        }
    }

}
