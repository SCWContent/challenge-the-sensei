package com.sensei.assertorder;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AssertOrderTestIncorrect {

    @Test
    public void testAssertOrder() {
        Integer[] integerArray = new Integer[]{1, 2};
        assertOrder(integerArray, 1, 2, 3);
    }

    void assertOrder(Object[] actual, Object... expected) {
        for (int i = 0; i < actual.length; i++) {
            assertThat(actual[i]).isEqualTo(expected[i]);
        }
        assertThat(actual.length).isEqualTo(actual.length);

    }
}
