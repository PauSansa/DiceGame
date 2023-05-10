package com.sansa.dicegame;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class dicegameApplicationTests {

    Calculator underTest = new Calculator();

    @Test
    void itShouldGiveNumbers() {
        //given
        int numberOne = 20;
        int numberTwo = 30;

        //when
        int result  = underTest.add(numberOne,numberTwo);

        //test
        int expected = 50;
        assertThat(result).isEqualTo(expected);
    }

    private class Calculator {
        int add(int a, int b){
            return a+b;
        }
    }
}
