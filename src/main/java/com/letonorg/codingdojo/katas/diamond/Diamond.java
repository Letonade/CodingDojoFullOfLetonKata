package com.letonorg.codingdojo.katas.diamond;

// https://codingdojo.org/kata/Diamond/
// Attention ! pas le droit au StringBuilder ou StringBuffer ou même Character
public class Diamond {

    public static String makeDiamond(char targetChar) {
        targetChar = transformToLegalCharacter(targetChar);
        if (targetChar == ' ') return "";

        int targetCharLevel = targetChar - 'A';
        int size = 2 * targetCharLevel + 1;
        String[] lines = new String[size];

        diamondForge(size, targetCharLevel, lines);

        return String.join("\n", lines);
    }

    private static void diamondForge(int size, int targetCharLevel, String[] lines) {
        for (int lineNumber = 0; lineNumber < size; lineNumber++) {
            int actualCharLevel = lineNumber;
            if (lineNumber > targetCharLevel) {
                actualCharLevel = size - 1 - lineNumber;
            }
            char actualChar = (char) ('A' + actualCharLevel);

            int nbOuterSpaces = targetCharLevel - actualCharLevel;
            int nbInnerSpaces = 0;
            if (actualCharLevel != 0) {
                nbInnerSpaces = 2 * actualCharLevel - 1;
            }

            lines[lineNumber] = buildLine(actualChar, nbOuterSpaces, nbInnerSpaces);
        }
    }

    private static String buildLine(char letter, int nbOuterSpaces, int nbInnerSpaces) {
        String line = "";

        line += addNbSpaces(nbOuterSpaces);
        line += letter;

        if (letter != 'A') {
            line += addNbSpaces(nbInnerSpaces);
            line += letter;
        }

        line += addNbSpaces(nbOuterSpaces);

        return line;
    }

    private static String addNbSpaces(int nb) {
        String result = "";
        for (int i = 0; i < nb; i++) {
            result += " ";
        }
        return result;
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
// Moins humaine et moins factorisé, on noteras que ca passe pas les tests...

//package com.letonorg.codingdojo.katas.diamond;
//
//public class Diamond {
//
//    private Diamond() {
//        // utility class
//    }
//
//    public static String makeDiamond(char letter) {
//        if (letter < 'A' || letter > 'Z') {
//            return "";
//        }
//
//        int n = letter - 'A';               // distance from A
//        int size = 2 * n + 1;               // height and width (if fully padded)
//        StringBuilder sb = new StringBuilder();
//
//        for (int row = 0; row < size; row++) {
//            int level = row <= n ? row : (size - 1 - row);   // 0..n..0
//            char c = (char) ('A' + level);
//
//            int outerSpaces = n - level;
//            int innerSpaces = level == 0 ? 0 : (2 * level - 1);
//
//            // left padding
//            appendSpaces(sb, outerSpaces);
//            sb.append(c);
//
//            // middle / second letter
//            if (level > 0) {
//                appendSpaces(sb, innerSpaces);
//                sb.append(c);
//            }
//
//            // newline except last line
//            if (row < size - 1) {
//                sb.append('\n');
//            }
//        }
//
//        return sb.toString();
//    }
//
//    private static void appendSpaces(StringBuilder sb, int count) {
//        for (int i = 0; i < count; i++) {
//            sb.append(' ');
//        }
//    }
//}
