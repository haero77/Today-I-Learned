package sample_code.design_pattern._12_proxy.patterns_example;

public class DefaultCalculatorProxyVer2 implements Calculator {

    private Calculator calculator;

    @Override
    public int calculate() {
        System.out.println("믿을 수 없겠지만, 계산이 완료될 때까지 10초 남았습니다.");

        // 지연 초기화(lazy initialization)
        if (calculator == null) {
            calculator = new DefaultCalculator();
        }
        int calculationResult = calculator.calculate(); // DefaultCalculator에게 계산을 요청한다.

        System.out.println("계산이 완료되었습니다.");
        return calculationResult;
    }
}
