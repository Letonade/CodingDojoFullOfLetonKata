package com.letonorg.codingdojo.katas.greed;

// https://codingdojo.org/kata/Greed/
public class Greed {
    public static int score(int[] diceRollArray) {
        if (diceRollArray.length < 1 || diceRollArray.length > 6) {
            return 0;
        }
        int score = 0;
        int[] frequencyArray =  new int[6];
        for (int rolledFace = 1; rolledFace <= 6; rolledFace++) {
            int numberFrequency = numberFrequencyCalculator(rolledFace, diceRollArray);
            frequencyArray[rolledFace-1] = (numberFrequency);
            score = getScoreOnFrequency(numberFrequency, rolledFace, score);
        }
        boolean straight = numberFrequencyCalculator(1,frequencyArray) == 6;
        boolean singleOne = frequencyArray[0] < 3 && frequencyArray[0] > 0;
        boolean singleFive = frequencyArray[4] < 3 && frequencyArray[4] > 0;
        boolean threePair = numberFrequencyCalculator(2, frequencyArray) >= 3;
        score = getScoreOnTotalFacesRolled(threePair, straight, singleOne, score, singleFive);

        return score;
    }

    private static int getScoreOnTotalFacesRolled(boolean threePair, boolean straight, boolean singleOne, int score, boolean singleFive) {
        if (!threePair && !straight && singleOne) {
            score += 100;
        }
        if (!threePair && !straight && singleFive) {
            score += 50;
        }
        if (straight) {
            score += 1200;
        }
        if (threePair) {
            score += 800;
        }
        return score;
    }

    private static int getScoreOnFrequency(int numberFrequency, int rolledFace, int score) {
        int multiplier = 1;
        if (numberFrequency > 3) {
            multiplier = 1 << (numberFrequency - 3);// 2 puiss freq/2 (freq/mult 4=2 5=3 6=8)
        }
        if (numberFrequency >= 3) {
            if (rolledFace != 1)
                score += rolledFace * 100 * multiplier;
            else
                score += rolledFace * 1000 * multiplier;
        }
        return score;
    }

    private static int numberFrequencyCalculator(int numberSearchedFor, int[] diceRollArray) {
        if (numberSearchedFor < 1 || numberSearchedFor > 6) {
            throw new IllegalArgumentException("numberSearchedFor must be > 0 and < 6");
        }
        int count = 0;
        for (int j : diceRollArray) {
            if (j == numberSearchedFor) {
                count++;
            }
        }
        return count;
    }
}

// Optimal chatGPT solution
// Basically, stocking comparaison then using a double if then both if,
// As the comparaison already has been made it optimize a little the code

//public class Greed {
//
//    public int score(int[] dice) {
//        if (dice == null || dice.length == 0) {
//            return 0;
//        }
//
//        int[] counts = new int[7]; // 1..6
//        for (int die : dice) {
//            if (die < 1 || die > 6) {
//                continue; // ou throw, selon ton besoin; ici on ignore l'invalide
//            }
//            counts[die]++;
//        }
//
//        if (isStraight(counts)) {
//            return 1200;
//        }
//
//        if (isThreePairs(counts)) {
//            return 800;
//        }
//
//        int score = 0;
//
//        // 4/5/6 of a kind: multiply the "triple" score
//        for (int face = 1; face <= 6; face++) {
//            int c = counts[face];
//            if (c >= 4) {
//                score += tripleScore(face) * multiplierFor(c);
//                counts[face] = 0; // consommé entièrement
//            }
//        }
//
//        // Triples
//        for (int face = 1; face <= 6; face++) {
//            if (counts[face] >= 3) {
//                score += tripleScore(face);
//                counts[face] -= 3;
//            }
//        }
//
//        // Singles (ta règle : non cumulative)
//        if (counts[1] > 0) {
//            score += 100;
//        }
//        if (counts[5] > 0) {
//            score += 50;
//        }
//
//        return score;
//    }
//
//    private boolean isStraight(int[] counts) {
//        for (int face = 1; face <= 6; face++) {
//            if (counts[face] != 1) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean isThreePairs(int[] counts) {
//        int pairs = 0;
//        for (int face = 1; face <= 6; face++) {
//            if (counts[face] == 2) {
//                pairs++;
//            } else if (counts[face] != 0) {
//                return false;
//            }
//        }
//        return pairs == 3;
//    }
//
//    private int tripleScore(int face) {
//        return (face == 1) ? 1000 : face * 100;
//    }
//
//    private int multiplierFor(int count) {
//        // 4 => x2, 5 => x4, 6 => x8 (selon tes tests)
//        return switch (count) {
//            case 4 -> 2;
//            case 5 -> 4;
//            case 6 -> 8;
//            default -> 1; // pas censé arriver ici
//        };
//    }
//}
