package sample_code.java_for_framework.interface_ex;

public class UserServiceEx {
    public static void main(String[] args) {
        UserService userService = new UserService(new KakaoLogin());
        userService.login();
    }
}
