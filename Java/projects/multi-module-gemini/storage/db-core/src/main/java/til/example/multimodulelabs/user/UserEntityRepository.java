package til.example.multimodulelabs.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import til.example.multimodulelabs.core.domain.user.domain.UserRepository;
import til.example.multimodulelabs.core.domain.user.exception.UserNotFound;
import til.example.multimodulelabs.core.domain.user.domain.User;
import til.example.multimodulelabs.core.domain.user.domain.UserId;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserEntityRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserId save(final User user) {
        final UserEntity saved = userJpaRepository.save(UserEntity.fromModel(user));
        return saved.toModel().getId();
    }

    @Override
    public Optional<User> findById(final UserId id) {
        return userJpaRepository.findById(id.value()).map(UserEntity::toModel);
    }

    @Override
    public User getById(final UserId id) {
        return findById(id).orElseThrow(() -> UserNotFound.from(id));
    }
}
