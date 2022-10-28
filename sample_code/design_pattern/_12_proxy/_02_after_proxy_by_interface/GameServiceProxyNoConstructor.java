package sample_code.design_pattern._12_proxy._02_after_proxy_by_interface;

public class GameServiceProxyNoConstructor implements GameService{

    private GameService gameService;

    @Override
    public void startGame() {
        long before = System.currentTimeMillis();

        // 지연초기화 - DefaultGameService 객체를 생성하는데 비용이 굉장히 비싼 경우에 사용하자.
        if (this.gameService == null) {
            this.gameService = new DefaultGameService();
        }

        gameService.startGame();
        System.out.println(System.currentTimeMillis() - before);
    }
}
