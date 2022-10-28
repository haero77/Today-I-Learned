package sample_code.design_pattern._12_proxy._01_before_proxy_by_inheritance;

public class Client {

    public static void main(String[] args) throws InterruptedException {
//        GameService gameService = new GameService();
        GameService gameService = new GameServiceProxy();
        gameService.startGame();
    }
}
