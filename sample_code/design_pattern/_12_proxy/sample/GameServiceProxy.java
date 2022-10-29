package sample_code.design_pattern._12_proxy.sample._02_after_proxy_by_interface;

public class GameServiceProxy implements sample_code.design_pattern._12_proxy.sample._02_after_proxy_by_interface.GameService {

    private sample_code.design_pattern._12_proxy.sample._02_after_proxy_by_interface.GameService gameService;

    public GameServiceProxy(sample_code.design_pattern._12_proxy.sample._02_after_proxy_by_interface.GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void startGame() {
        long before = System.currentTimeMillis();
        gameService.startGame();
        System.out.println(System.currentTimeMillis() - before);
    }
}
