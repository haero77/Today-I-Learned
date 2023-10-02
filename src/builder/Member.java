package builder;

import lombok.Builder;

@Builder
public class Member {

    private String username;
    private String email;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
