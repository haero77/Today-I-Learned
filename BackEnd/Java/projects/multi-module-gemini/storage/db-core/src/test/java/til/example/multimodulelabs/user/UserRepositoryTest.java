package til.example.multimodulelabs.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import til.example.multimodulelabs.ContextTest;
import til.example.multimodulelabs.core.domain.user.domain.User;
import til.example.multimodulelabs.core.domain.user.domain.UserId;
import til.example.multimodulelabs.core.domain.user.domain.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserRepositoryTest extends ContextTest {

    @Autowired
    UserRepository sut;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Test
    void getById() {
        // given
        final UserEntity userEntity = UserEntity.builder()
                .name("name")
                .build();
        userJpaRepository.save(userEntity);

        final UserId id = new UserId(userEntity.getId());

        // when
        final User actual = sut.getById(id);

        // then
        assertAll(
                () -> assertThat(actual.getId()).isEqualTo(new UserId(userEntity.getId())),
                () -> assertThat(actual.getName()).isEqualTo("name")
        );
    }
}