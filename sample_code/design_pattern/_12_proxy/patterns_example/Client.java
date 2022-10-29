package sample_code.design_pattern._12_proxy.patterns_example;

public class Client {

    public static void main(String[] args) {
        Calculator calculator = new DefaultCalculator();
        calculator.calculate();
    }
}
