package til.example.multimodulelabs.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<NewUserResponse> appendUser(@RequestBody final NewUserRequest request) {
        final UserId userId = userService.append(request.getName());

        final NewUserResponse responseBody = new NewUserResponse(userId.value());

        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserResponse> findUser(final @PathVariable Long userId) {
        final User found = userService.read(new UserId(userId));

        final UserResponse responseBody = UserResponse.from(found);

        return ResponseEntity.ok().body(responseBody);
    }
}
