package org.example.item06;

public class AutoBoxing {

    public static long sum() {
        long sum = 0L;

        for (long i = 0; i < Integer.MAX_VALUE; i++) { // Long 인스턴스가 2^31 개 쌓인다.
            sum += i; // 오토박싱. 메서드 스택 프레임에 Long 인스턴스가 쌓인다.
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sum());
    }

}
