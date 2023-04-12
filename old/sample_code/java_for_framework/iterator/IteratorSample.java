package old.sample_code.java_for_framework.iterator;

import old.sample_code.java_for_framework.collection.MyCollection;

import java.util.Arrays;

public class IteratorSample {
    public static void main(String[] args) {
        MyIterator<String> iterator = new MyCollection<>(Arrays.asList("A", "BB", "CCC", "DDDD", "EEEEE"))
                .iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.hasNext());
        }
    }
}
