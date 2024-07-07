package til.example.multimodulelabs.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
