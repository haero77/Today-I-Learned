package til.example.multimodulelabs.core.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import til.example.multimodulelabs.core.domain.user.domain.User;
import til.example.multimodulelabs.core.domain.user.domain.UserId;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserId append(final String name) {
        final User newUser = User.fromName(name);
        return userRepository.save(newUser);
    }

    public User read(final UserId id) {
        return userRepository.getById(id);
    }
}
