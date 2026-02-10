package com.letonorg.codingdojo.katas.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

// https://codingdojo.org/kata/FizzBuzz/
public class FizzBuzz {

    static void main() {
    }

    public static List<String> fizzBuzzRangeOfNumbers(int startNumber, int endNumber) {
        if (startNumber > endNumber) {
            throw new IllegalArgumentException("startNumber must be <= endNumber");
        }

        List<String> listOfFizzBuzz = new ArrayList<>();

        for (int number = startNumber; number <= endNumber; number++) {
            listOfFizzBuzz.add(fizzBuzzNumber(number));
        }

        return listOfFizzBuzz;
    }

    public static String fizzBuzzNumber(int number) {
        var result = "";
        if (number % 3 == 0) {
            result += "Fizz";
        }

        if (number % 5 == 0) {
            result += "Buzz";
        }

        if (result.isEmpty()){
            result = String.valueOf(number);
        }
        return result;
    }
}

// Optimal chatGPT solution
// Basically, stocking comparaison then using a double if then both if,
// As the comparaison already has been made it optimize a little the code

//public class FizzBuzz {
//
//    public static List<String> fizzBuzzRangeOfNumbers(int startNumber, int endNumber) {
//        if (startNumber > endNumber) {
//            throw new IllegalArgumentException("startNumber must be <= endNumber");
//        }
//
//        int size = endNumber - startNumber + 1;
//        List<String> listOfFizzBuzz = new ArrayList<>(size);
//
//        for (int number = startNumber; number <= endNumber; number++) {
//            listOfFizzBuzz.add(fizzBuzzNumber(number));
//        }
//
//        return listOfFizzBuzz;
//    }
//
//    public static String fizzBuzzNumber(int number) {
//        boolean divisibleBy3 = number % 3 == 0;
//        boolean divisibleBy5 = number % 5 == 0;
//
//        if (divisibleBy3 && divisibleBy5) return "FizzBuzz";
//        if (divisibleBy3) return "Fizz";
//        if (divisibleBy5) return "Buzz";
//        return String.valueOf(number);
//    }
//}
