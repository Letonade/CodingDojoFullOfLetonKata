package com.letonorg.codingdojo.katas.diamond;

import java.util.Arrays;

// https://codingdojo.org/kata/Diamond/
// Attention ! pas le droit au StringBuilder ou StringBuffer ou même Character
public class DiamondGeo {

    public static String makeDiamond(char targetChar) {
        targetChar = transformToLegalCharacter(targetChar);
        if (targetChar == ' ') return "";

        int linesSquareSize = ((targetChar - 'A') * 2) + 1;
        int horizontalMidPosition = linesSquareSize / 2 + linesSquareSize % 2 - 1;

        char[][] resultArray = new char[linesSquareSize][linesSquareSize];
        blankFill(linesSquareSize, resultArray);
        diamondFill(targetChar, horizontalMidPosition, resultArray, linesSquareSize);

        String result = "";
        result = convertCharArrayToString(resultArray, result);

        System.out.print(result);
        return result;
    }

    private static String convertCharArrayToString(char[][] resultArray, String result) {
        for (char[] row : resultArray) {
            result += new String(row) + "\n";
        }
        return result;
    }

    private static void diamondFill(char targetChar,
                                    int horizontalMidPosition,
                                    char[][] resultArray,
                                    int linesSquareSize) {
        for (int actualChar = 'A'; actualChar <= targetChar; actualChar++) {
            int letterNumber = (actualChar - 'A');
            int leftPosition = horizontalMidPosition - letterNumber;
            int rightPosition = horizontalMidPosition + letterNumber;

            resultArray[letterNumber][leftPosition] = (char) actualChar;
            resultArray[letterNumber][rightPosition] = (char) actualChar;
            resultArray[linesSquareSize - letterNumber - 1][leftPosition] = (char) actualChar;
            resultArray[linesSquareSize - letterNumber - 1][rightPosition] = (char) actualChar;
        }
    }

    private static void blankFill(int linesSquareSize, char[][] resultArray) {
        for (int i = 0; i < linesSquareSize; i++) {
            Arrays.fill(resultArray[i], ' ');
        }
    }

    private static char transformToLegalCharacter(char examinedChar) {
        if (examinedChar >= 'a' && examinedChar <= 'z') {
            return (char) (examinedChar - 32);
        }
        if (examinedChar >= 'A' && examinedChar <= 'Z') {
            return examinedChar;
        }
        return ' ';
    }
}


// La version ChatGPT
// Ca version à l'air plus efficace pour le coup.

//import java.util.Arrays;
//
//public class DiamondGeo {
//
//    public static String makeDiamond(char targetChar) {
//        char letter = toValidUppercaseLetterOrZero(targetChar);
//        if (letter == 0) return "";
//
//        int n = letter - 'A';
//        int size = 2 * n + 1;
//        int mid = size / 2;
//
//        char[][] grid = new char[size][size];
//        fillWithSpaces(grid);
//        placeLetters(letter, mid, grid);
//
//        return toString(grid);
//    }
//
//    private static void placeLetters(char targetChar, int mid, char[][] grid) {
//        int size = grid.length;
//
//        for (char c = 'A'; c <= targetChar; c++) {
//            int level = c - 'A';
//            int left = mid - level;
//            int right = mid + level;
//            int top = level;
//            int bottom = size - 1 - level;
//
//            grid[top][left] = c;
//            grid[top][right] = c;
//            grid[bottom][left] = c;
//            grid[bottom][right] = c;
//        }
//    }
//
//    private static void fillWithSpaces(char[][] grid) {
//        for (char[] row : grid) {
//            Arrays.fill(row, ' ');
//        }
//    }
//
//    private static String toString(char[][] grid) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < grid.length; i++) {
//            sb.append(grid[i]);
//            if (i < grid.length - 1) sb.append('\n');
//        }
//        return sb.toString();
//    }
//
//    private static char toValidUppercaseLetterOrZero(char c) {
//        char u = Character.toUpperCase(c);
//        return (u >= 'A' && u <= 'Z') ? u : 0;
//    }
//}
