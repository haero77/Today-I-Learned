package sample_code.java_for_framework.java_interface.functions_of_interface;

public class UserServiceEx {
    public static void main(String[] args) {
        UserService userService = new UserService(new KakaoLogin());
        userService.login();
    }
}
