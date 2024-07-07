package til.example.multimodulelabs.user;

import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String name;

    public UserResponse(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static UserResponse from(final User user) {
        return new UserResponse(user.getIdValue(), user.getName());
    }
}
