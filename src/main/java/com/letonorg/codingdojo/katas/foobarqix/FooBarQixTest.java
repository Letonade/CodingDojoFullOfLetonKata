package com.letonorg.codingdojo.katas.foobarqix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FooBarQixTest {

    @ParameterizedTest(name = "compute({0}) => {1}")
    @MethodSource("step1_examples")
    void step1_examples(String input, String expected) {
        assertEquals(expected, FooBarQix.compute(input));
    }

    static Stream<Arguments> step1_examples() {
        return Stream.of(
                Arguments.of("1", "1"),
                Arguments.of("2", "2"),
                Arguments.of("3", "FooFoo"),
                Arguments.of("4", "4"),
                Arguments.of("5", "BarBar"),
                Arguments.of("6", "Foo"),
                Arguments.of("7", "QixQix"),
                Arguments.of("8", "8"),
                Arguments.of("9", "Foo"),
                Arguments.of("10", "Bar*"),
                Arguments.of("13", "Foo"),
                Arguments.of("15", "FooBarBar"),
                Arguments.of("21", "FooQix"),
                Arguments.of("33", "FooFooFoo"),
                Arguments.of("51", "FooBar"),
                Arguments.of("53", "BarFoo")
        );
    }

    @ParameterizedTest(name = "compute({0}) => {1}")
    @MethodSource("step2_examples")
    void step2_examples_zero_becomes_asterisk(String input, String expected) {
        assertEquals(expected, FooBarQix.compute(input));
    }

    static Stream<Arguments> step2_examples() {
        return Stream.of(
                Arguments.of("101", "1*1"),
                Arguments.of("303", "FooFoo*Foo"),
                Arguments.of("105", "FooBarQix*Bar"),
                Arguments.of("10101", "FooQix**")
        );
    }
}
