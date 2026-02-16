package com.letonorg.codingdojo.katas.foobarqix;

public class FooBarQix {
    public static String compute(String numberAsString) {
        int number = Integer.parseInt(numberAsString);
        String composedFooBarQix = "";
        if (number % 3 == 0) composedFooBarQix += "Foo";
        if (number % 5 == 0) composedFooBarQix += "Bar";
        if (number % 7 == 0) composedFooBarQix += "Qix";

        char[] splittedNumber = numberAsString.toCharArray();
        for (char numberAt : splittedNumber){
            switch (numberAt)
                {
                    case '3' -> composedFooBarQix += "Foo";
                    case '5' -> composedFooBarQix += "Bar";
                    case '7' -> composedFooBarQix += "Qix";
                    case '0' -> composedFooBarQix += "*";
                }
        }

        if (!composedFooBarQix.isEmpty()){
            if (isOnlyStarsOrEmpty(composedFooBarQix))
                return numberAsString.replace('0', '*');
            return composedFooBarQix;
        }
        return numberAsString;
    }

    private static boolean isOnlyStarsOrEmpty(String composedFooBarQix) {
        return composedFooBarQix.replace("*", "").isEmpty();
    }

}

//Version de ChatGPT
// Incorrecte !! ChatGPT à halluciner le code à produire mais à bien fait les tests.
// Il ne s'est même pas donner la peine de faire tourner son code pour vérifier et à fait une erreur.

//public final class FooBarQix {
//
//    private FooBarQix() { }
//
//    public static String compute(String input) {
//        if (input == null || input.isBlank()) {
//            throw new IllegalArgumentException("input must be a non-empty numeric string");
//        }
//        if (!input.chars().allMatch(Character::isDigit)) {
//            throw new IllegalArgumentException("input must contain digits only");
//        }
//
//        int number = Integer.parseInt(input);
//
//        StringBuilder result = new StringBuilder();
//
//        // Divisibility rules
//        if (number % 3 == 0) result.append("Foo");
//        if (number % 5 == 0) result.append("Bar");
//        if (number % 7 == 0) result.append("Qix");
//
//        // Digit rules (in order)
//        for (int i = 0; i < input.length(); i++) {
//            char c = input.charAt(i);
//            if (c == '3') result.append("Foo");
//            else if (c == '5') result.append("Bar");
//            else if (c == '7') result.append("Qix");
//            else if (c == '0') result.append("*");
//        }
//
//        // Fallback
//        return result.length() == 0 ? input : result.toString();
//    }
//}


// Venant de ChatGPT:
//🎯 Conclusion
//
//Non.
//Ma version ne respecte pas la règle officielle si on attend :
//        101 → "1*1"
//Elle retourne :
//        "*"