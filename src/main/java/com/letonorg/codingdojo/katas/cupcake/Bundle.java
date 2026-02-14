package com.letonorg.codingdojo.katas.cupcake;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bundle implements Cake {
    private final List<Cake> cakes;
    private double bundleReduction = 0.9;

    public Bundle(Cake... cakes) {
        this.cakes = Arrays.asList(cakes);
    }

    @Override
    public String name() {
        return "Bundle of: " + cakes.stream()
                .map(Cake::name)
                .collect(Collectors.joining(", "));
    }

    @Override
    public double price() {
        double total = cakes.stream()
                .mapToDouble(Cake::price)
                .sum();

        return total * bundleReduction;
    }
}

// Version de ChatGPT

//package com.letonorg.codingdojo.katas.cupcake;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class Bundle implements Cake {
//
//    private final List<Cake> cakes;
//
//    public Bundle(Cake... cakes) {
//        this.cakes = Arrays.asList(cakes);
//    }
//
//    @Override
//    public String name() {
//        StringBuilder builder = new StringBuilder("Bundle of ");
//
//        for (int i = 0; i < cakes.size(); i++) {
//            builder.append(cakes.get(i).name());
//            if (i < cakes.size() - 1) {
//                builder.append(", ");
//            }
//        }
//
//        return builder.toString();
//    }
//
//    @Override
//    public double price() {
//        double total = cakes.stream()
//                .mapToDouble(Cake::price)
//                .sum();
//
//        return total * 0.9; // 10% discount
//    }
//}
