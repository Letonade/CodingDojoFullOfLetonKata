package com.letonorg.codingdojo.katas.fizzbuzz;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {

    @Test
    public void should_return_number_as_string_when_not_divisible_by_3_or_5() {

        assertEquals("1", FizzBuzz.fizzBuzzNumber(1));
        assertEquals("2", FizzBuzz.fizzBuzzNumber(2));
        assertEquals("4", FizzBuzz.fizzBuzzNumber(4));
    }

    @Test
    public void should_return_fizz_when_divisible_by_3() {

        assertEquals("Fizz", FizzBuzz.fizzBuzzNumber(3));
        assertEquals("Fizz", FizzBuzz.fizzBuzzNumber(6));
        assertEquals("Fizz", FizzBuzz.fizzBuzzNumber(9));
    }

    @Test
    public void should_return_buzz_when_divisible_by_5() {

        assertEquals("Buzz", FizzBuzz.fizzBuzzNumber(5));
        assertEquals("Buzz", FizzBuzz.fizzBuzzNumber(10));
        assertEquals("Buzz", FizzBuzz.fizzBuzzNumber(20));
    }

    @Test
    public void should_return_fizzbuzz_when_divisible_by_3_and_5() {

        assertEquals("FizzBuzz", FizzBuzz.fizzBuzzNumber(15));
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzzNumber(30));
    }

    @Test
    public void should_handle_sequence_consistently() {

        assertEquals("FizzBuzz", FizzBuzz.fizzBuzzNumber(45));
        assertEquals("Fizz", FizzBuzz.fizzBuzzNumber(99));
        assertEquals("Buzz", FizzBuzz.fizzBuzzNumber(100));
    }

    @Test
    public void should_convert_numbers_from_1_to_100_according_to_fizzbuzz_rules() {
        List<String> result = FizzBuzz.fizzBuzzRangeOfNumbers(1, 100);

        assertEquals(100, result.size());

        assertEquals("1", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("Fizz", result.get(2));
        assertEquals("4", result.get(3));
        assertEquals("Buzz", result.get(4));
        assertEquals("FizzBuzz", result.get(14));
        assertEquals("Buzz", result.get(99));
    }

}
