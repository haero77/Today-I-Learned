package com.example.demo.post.domain;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    @DisplayName("PostCreate 으로 게시글을 만들 수 있다.")
    @Test
    void createPostByPostCreate() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .writerId(1)
                .content("content")
                .build();

        User writer = User.builder()
                .id(1L)
                .email("example@email.com")
                .nickname("nickname")
                .address("address")
                .certificationCode("certificationCode")
                .status(UserStatus.ACTIVE)
                .build();

        // when
        Post post = Post.of(writer, postCreate);

        // then
        assertThat(post.getContent()).isEqualTo("content");
        assertThat(post.getWriter().getEmail()).isEqualTo("example@email.com");
        assertThat(post.getWriter().getNickname()).isEqualTo("nickname");
        assertThat(post.getWriter().getAddress()).isEqualTo("address");
        assertThat(post.getWriter().getCertificationCode()).isEqualTo("certificationCode");
        assertThat(post.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @DisplayName("PostUpdate 로 게시글을 업데이트할 수 있다.")
    @Test
    void updatePostByPostUpdate() {
        // given

        // when

        // then
//        assertThat()
    }

}