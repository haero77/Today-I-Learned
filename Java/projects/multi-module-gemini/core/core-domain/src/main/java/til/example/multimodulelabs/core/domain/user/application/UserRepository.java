package til.example.multimodulelabs.core.domain.user.application;

import til.example.multimodulelabs.core.domain.user.domain.User;
import til.example.multimodulelabs.core.domain.user.domain.UserId;

import java.util.Optional;

public interface UserRepository {

    UserId save(User user);

    Optional<User> findById(UserId id);

    User getById(UserId id);
}
