package til.example.multimodulelabs.user;

import java.util.Optional;

public interface UserRepository {

    UserId save(User user);

    Optional<User> findById(UserId id);

    User getById(UserId id);
}
