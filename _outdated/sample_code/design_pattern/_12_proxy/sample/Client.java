package sample_code.design_pattern._12_proxy.sample._02_after_proxy_by_interface;


import sample_code.design_pattern._12_proxy.sample.DefaultGameService;
import sample_code.design_pattern._12_proxy.sample.GameService;
import sample_code.design_pattern._12_proxy.sample.GameServiceProxy;

pub0lic class Client {

    public static void main(String[] args) {
        GameService gameService = new GameServiceProxy(new DefaultGameService());
        gameService.startGame();
    }
}
