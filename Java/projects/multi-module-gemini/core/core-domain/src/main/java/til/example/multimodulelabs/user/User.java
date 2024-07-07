package til.example.multimodulelabs.user;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class User {

    private final UserId id;
    private final String name;

    @Builder
    private User(final UserId id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static User fromName(String name) {
        return User.builder()
                .name(name)
                .build();
    }

    public Long getIdValue() {
        if (Objects.isNull(this.id)) {
            return null;
        }
        return this.id.value();
    }
}
