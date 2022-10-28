package sample_code.design_pattern._12_proxy._01_before_proxy_by_inheritance;

// 인터페이스가 없는 경우에는 상속을 이용
public class GameServiceProxy extends GameService {

    @Override
    public void startGame() throws InterruptedException {
        long before = System.currentTimeMillis();
        super.startGame();
        System.out.println(System.currentTimeMillis() - before);
    }
}
