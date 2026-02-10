package com.letonorg.codingdojo.katas.greed;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreedTest {

    private final Greed greed = new Greed();

    @Test
    @DisplayName("Empty roll scores 0")
    void empty_roll_scores_0() {
        assertEquals(0, greed.score(new int[]{}));
    }

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("singleDieCases")
    @DisplayName("Single die scoring")
    void single_die_scoring(int[] dice, int expected) {
        assertEquals(expected, greed.score(dice));
    }

    static Stream<Object[]> singleDieCases() {
        return Stream.of(
                new Object[]{new int[]{1}, 100},
                new Object[]{new int[]{5}, 50},
                new Object[]{new int[]{2}, 0},
                new Object[]{new int[]{3}, 0},
                new Object[]{new int[]{4}, 0},
                new Object[]{new int[]{6}, 0}
        );
    }

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("tripleCases")
    @DisplayName("Triples scoring")
    void triples_scoring(int[] dice, int expected) {
        assertEquals(expected, greed.score(dice));
    }

    static Stream<Object[]> tripleCases() {
        return Stream.of(
                new Object[]{new int[]{1, 1, 1}, 1000},
                new Object[]{new int[]{2, 2, 2}, 200},
                new Object[]{new int[]{3, 3, 3}, 300},
                new Object[]{new int[]{4, 4, 4}, 400},
                new Object[]{new int[]{5, 5, 5}, 500},
                new Object[]{new int[]{6, 6, 6}, 600}
        );
    }

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("mixedCases")
    @DisplayName("Mixed rolls (triples + singles)")
    void mixed_rolls(int[] dice, int expected) {
        assertEquals(expected, greed.score(dice));
    }

    static Stream<Object[]> mixedCases() {
        return Stream.of(
                // triple + extra ones/fives
                new Object[]{new int[]{1, 1, 1, 1}, 2000},     // 1000*2
                new Object[]{new int[]{1, 1, 1, 5}, 1050},     // 1000 + 50
                new Object[]{new int[]{5, 5, 5, 1}, 600},      // 500 + 100
                new Object[]{new int[]{2, 2, 2, 5, 5}, 250},   // 200 + 50
                // no scoring dice besides singles
                new Object[]{new int[]{1, 5}, 150},
                // nothing scores
                new Object[]{new int[]{2, 3, 4, 6}, 0}
        );
    }

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("nOfAKindCases")
    @DisplayName("Four/Five/Six of a kind multiply the triple score")
    void n_of_a_kind(int[] dice, int expected) {
        assertEquals(expected, greed.score(dice));
    }

    static Stream<Object[]> nOfAKindCases() {
        return Stream.of(
                // Four-of-a-kind: triple score * 2
                new Object[]{new int[]{2, 2, 2, 2}, 400},      // 200 * 2
                new Object[]{new int[]{1, 1, 1, 1}, 2000},     // 1000 * 2

                // Five-of-a-kind: triple score * 4
                new Object[]{new int[]{3, 3, 3, 3, 3}, 1200},  // 300 * 4
                new Object[]{new int[]{5, 5, 5, 5, 5}, 2000},  // 500 * 4

                // Six-of-a-kind: triple score * 8
                new Object[]{new int[]{6, 6, 6, 6, 6, 6}, 4800}, // 600 * 8
                new Object[]{new int[]{1, 1, 1, 1, 1, 1}, 8000}  // 1000 * 8
        );
    }

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("threePairsCases")
    @DisplayName("Three pairs scores 800 even with singles")
    void three_pairs_scores_800(int[] dice, int expected) {
        assertEquals(expected, greed.score(dice));
    }

    static Stream<Object[]> threePairsCases() {
        return Stream.of(
                new Object[]{new int[]{2, 2, 3, 3, 4, 4}, 800}, // 800
                new Object[]{new int[]{1, 1, 3, 3, 4, 4}, 800},  // 800
                new Object[]{new int[]{1, 1, 3, 3, 5, 5}, 800}  // 800
        );
    }


    @Test
    @DisplayName("Straight scores 1200")
    void straight_scores_1200() {
        assertEquals(1200, greed.score(new int[]{1, 2, 3, 4, 5, 6}));
    }

    @ParameterizedTest(name = "{0} => {1}")
    @MethodSource("orderIndependenceCases")
    @DisplayName("Order does not matter")
    void order_does_not_matter(int[] dice, int expected) {
        assertEquals(expected, greed.score(dice));
    }

    static Stream<Object[]> orderIndependenceCases() {
        return Stream.of(
                new Object[]{new int[]{1, 5, 1, 1, 1}, 2050}, // 2000 + 50
                new Object[]{new int[]{5, 1, 1, 1, 1}, 2050}  // 2000 + 50
        );
    }
}
