package old.sample_code.design_pattern._12_proxy.patterns_example;

public class DefaultCalculatorProxy implements Calculator{

    private Calculator calculator;

    @Override
    public int calculate() {
        System.out.println("요청하신 작업이 수행중입니다.");

        if (calculator == null) {
            calculator = new DefaultCalculator();
        }
        return calculator.calculate();
    }
}
