package com.estsoft.springproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("1+2는 3이다.")
    @Test
    public void test() {
        // Given
        int i = 1;
        int j = 2;

        // When
        int sum = i + j;

        // Then
        org.junit.jupiter.api.Assertions.assertEquals(3, sum);
        org.assertj.core.api.Assertions.assertThat(sum).isEqualTo(3);
    }
}
