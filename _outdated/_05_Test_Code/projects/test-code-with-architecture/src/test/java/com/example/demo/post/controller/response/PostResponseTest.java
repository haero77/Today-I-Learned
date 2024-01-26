package com.example.demo.post.controller.response;

import com.example.demo.post.domain.Post;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostResponseTest {

    /**
     * Post 를 HTTP 응답으로 변환하는 테스트
     */
    @DisplayName("Post 로 응답을 생성할 수 있다.")
    @Test
    void createPostResponse() {
        // given
        Post post = Post.builder()
                .content("content")
                .writer(User.builder()
                        .id(1L)
                        .email("example@email.com")
                        .nickname("nickname")
                        .address("address")
                        .certificationCode("certificationCode")
                        .status(UserStatus.ACTIVE)
                        .build()
                )
                .build();

        // when
        PostResponse postResponse = PostResponse.from(post);

        // then
        assertThat(postResponse.getContent()).isEqualTo("content");
        assertThat(postResponse.getWriter().getEmail()).isEqualTo("example@email.com");
        assertThat(postResponse.getWriter().getNickname()).isEqualTo("nickname");
        assertThat(postResponse.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

}