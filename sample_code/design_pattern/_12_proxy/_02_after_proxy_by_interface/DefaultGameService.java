package sample_code.design_pattern._12_proxy._02_after_proxy_by_interface;

public class DefaultGameService implements GameService{

    @Override
    public void startGame() {
        System.out.println("이 자리에 오신 여러분을 진심으로 환영합니다.");
    }
}
