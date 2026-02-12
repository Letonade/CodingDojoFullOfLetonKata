package com.letonorg.codingdojo.katas.nearest_color;

import java.util.ArrayList;
import java.util.List;

// https://codingdojo.org/kata/NearestColor/
public class NearestColor {
    public static List<String> nearestColorsOf(String rgb){
        if (rgb.length() != 3){
            throw new IllegalArgumentException("rgb length should be 3");
        }
        System.out.println("rgb => "+rgb);
        int redInt = Integer.parseInt(rgb.substring(0, 1), 16);
        int greenInt = Integer.parseInt(rgb.substring(1, 2), 16);
        int blueInt = Integer.parseInt(rgb.substring(2, 3), 16);
        System.out.println("redInt => "+redInt+", greenInt => "+greenInt+", blueInt => "+blueInt);

        boolean isRedMax = redInt >= blueInt && redInt >= greenInt;
        boolean isGreenMax = greenInt >= blueInt && greenInt >= redInt;
        boolean isBlueMax = blueInt >= redInt && blueInt >= greenInt;
        System.out.println("isRedMax => "+isRedMax+", isGreenMax => "+isGreenMax+", isBlueMax => "+isBlueMax );

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
