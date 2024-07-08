package til.example.multimodulelabs.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import til.example.multimodulelabs.BaseJpaEntity;
import til.example.multimodulelabs.core.domain.user.domain.User;
import til.example.multimodulelabs.core.domain.user.domain.UserId;

@Getter
@Table(name = "users")
@Entity
public class UserEntity extends BaseJpaEntity {

    private String name;

    protected UserEntity() {
    }

    @Builder
    private UserEntity(final Long id, final String name) {
        super.id = id;
        this.name = name;
    }

    public static UserEntity fromModel(final User user) {
        return UserEntity.builder()
                .id(user.getIdValue())
                .name(user.getName())
                .build();
    }

    public User toModel() {
        return User.builder()
                .id(new UserId(super.id))
                .name(this.name)
                .build();
    }
}
