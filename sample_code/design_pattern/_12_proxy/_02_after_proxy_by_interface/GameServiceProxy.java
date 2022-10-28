package sample_code.design_pattern._12_proxy._02_after_proxy_by_interface;

public class GameServiceProxy implements GameService{

    private GameService gameService;

    public GameServiceProxy(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void startGame() {
        long before = System.currentTimeMillis();
        gameService.startGame();
        System.out.println(System.currentTimeMillis() - before);
    }
}
