package til.example.multimodulelabs.user;

import lombok.Getter;

@Getter
public class NewUserResponse {

    private final Long id;

    public NewUserResponse(final Long id) {
        this.id = id;
    }
}
