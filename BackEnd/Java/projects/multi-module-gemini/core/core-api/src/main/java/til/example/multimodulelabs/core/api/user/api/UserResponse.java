package til.example.multimodulelabs.core.api.user.api;

import lombok.Getter;
import til.example.multimodulelabs.core.domain.user.domain.User;

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
