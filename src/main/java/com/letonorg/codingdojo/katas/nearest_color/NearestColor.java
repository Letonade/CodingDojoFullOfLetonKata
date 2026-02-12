package com.letonorg.codingdojo.katas.nearest_color;

import java.util.ArrayList;
import java.util.List;

// https://codingdojo.org/kata/NearestColor/
public class NearestColor {
    public static List<String> nearestColorsOf(String rgb){
        if (rgb.length() != 3){
            throw new IllegalArgumentException("rgb length should be 3");
        }

        int redInt = Integer.parseInt(rgb.substring(0, 1), 16);
        int greenInt = Integer.parseInt(rgb.substring(1, 2), 16);
        int blueInt = Integer.parseInt(rgb.substring(2, 3), 16);

        boolean isRedMax = redInt >= blueInt && redInt >= greenInt;
        boolean isGreenMax = greenInt >= blueInt && greenInt >= redInt;
        boolean isBlueMax = blueInt >= redInt && blueInt >= greenInt;

        List<String> result = new ArrayList<>();
        if (isRedMax)
            result.add("F00");
        if (isGreenMax)
            result.add("0F0");
        if (isBlueMax)
            result.add("00F");

        return result;
    }
}

// La solution de ChatGPT
//Il décide d'utiliser la distance euclidienne mais mathématiquement parlant est incapable de me prouver que
// mon approche par recherche de la couleur dominante n'est pas la plus courte, un bonne exercice pour repérer les
// utilisateur de chatGPT

//package com.letonorg.codingdojo.katas.nearest_color;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class NearestColor {
//
//    private static final String RED = "F00";
//    private static final String GREEN = "0F0";
//    private static final String BLUE = "00F";
//
//    public static List<String> nearestColorsOf(String color) {
//        int[] rgb = parseRgbNibbles(color);
//
//        int redDist = squaredDistance(rgb, new int[]{15, 0, 0});
//        int greenDist = squaredDistance(rgb, new int[]{0, 15, 0});
//        int blueDist = squaredDistance(rgb, new int[]{0, 0, 15});
//
//        int min = Math.min(redDist, Math.min(greenDist, blueDist));
//
//        List<String> result = new ArrayList<>();
//        if (redDist == min) result.add(RED);
//        if (greenDist == min) result.add(GREEN);
//        if (blueDist == min) result.add(BLUE);
//
//        return result;
//    }
//
//    private static int squaredDistance(int[] a, int[] b) {
//        int dr = a[0] - b[0];
//        int dg = a[1] - b[1];
//        int db = a[2] - b[2];
//        return dr * dr + dg * dg + db * db;
//    }
//
//    private static int[] parseRgbNibbles(String color) {
//        // Kata statement doesn't define validation, so keep it minimal and assume valid 3-hex uppercase.
//        int r = hexNibble(color.charAt(0));
//        int g = hexNibble(color.charAt(1));
//        int b = hexNibble(color.charAt(2));
//        return new int[]{r, g, b};
//    }
//
//    private static int hexNibble(char c) {
//        if (c >= '0' && c <= '9') return c - '0';
//        if (c >= 'A' && c <= 'F') return 10 + (c - 'A');
//        if (c >= 'a' && c <= 'f') return 10 + (c - 'a'); // extra leniency
//        throw new IllegalArgumentException("Invalid hex digit: " + c);
//    }
//}

