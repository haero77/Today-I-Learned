package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @DisplayName("테스트 환경 구축 테스트")
    @Test
    void test() {
        assertThat(true).isTrue();
    }

}