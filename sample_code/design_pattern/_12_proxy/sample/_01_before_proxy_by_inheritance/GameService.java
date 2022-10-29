package sample_code.design_pattern._12_proxy.sample._01_before_proxy_by_inheritance;

public class GameService {

    // 이 GameService 코드를 건드리지 않고 어떻게 활용할 수 있을까?
    public void startGame() throws InterruptedException {
        System.out.println("이 자리에 오신 여러분을 진심으로 환영합니다.");
        Thread.sleep(1000L);
    }

}
