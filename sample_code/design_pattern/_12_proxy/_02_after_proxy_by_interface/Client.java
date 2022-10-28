package sample_code.design_pattern._12_proxy._02_after_proxy_by_interface;


public class Client {

    public static void main(String[] args) {
        GameService gameService = new GameServiceProxy(new DefaultGameService());
        gameService.startGame();
    }
}
