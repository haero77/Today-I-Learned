package til.example.multimodulelabs.core.domain.user.exception;

import til.example.multimodulelabs.core.domain.user.domain.UserId;

public class UserNotFound extends RuntimeException{

    public UserNotFound(final String message) {
        super(message);
    }

    public static UserNotFound from(final UserId id) {
        return new UserNotFound("Cannot find User for userId=%d".formatted(id.value()));
    }
}
