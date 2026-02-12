package com.letonorg.codingdojo.katas.nearest_color;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NearestColorTest {

    /**
     * Contract:
     * Given a 3-hex-digit RGB color (e.g. "F42"),
     * return ALL nearest colors among:
     * - "F00" (red)
     * - "0F0" (green)
     * - "00F" (blue)
     *
     * If only one is nearest → return a list with one element.
     * If several are equally near → return all of them.
     *
     * Order does NOT matter.
     */

    // -----------------------------
    // Exact matches
    // -----------------------------

    @Test
    void _exact_red_returns_red_only() {
        assertEquals(Set.of("F00"), asSet(NearestColor.nearestColorsOf("F00")));
    }

    @Test
    void _exact_green_returns_green_only() {
        assertEquals(Set.of("0F0"), asSet(NearestColor.nearestColorsOf("0F0")));
    }

    @Test
    void _exact_blue_returns_blue_only() {
        assertEquals(Set.of("00F"), asSet(NearestColor.nearestColorsOf("00F")));
    }

    // -----------------------------
    // Simple nearest cases
    // -----------------------------

    @Test
    void _F42_is_nearest_to_red() {
        assertEquals(Set.of("F00"), asSet(NearestColor.nearestColorsOf("F42")));
    }

    @Test
    void _4F2_is_nearest_to_green() {
        assertEquals(Set.of("0F0"), asSet(NearestColor.nearestColorsOf("4F2")));
    }

    @Test
    void _24F_is_nearest_to_blue() {
        assertEquals(Set.of("00F"), asSet(NearestColor.nearestColorsOf("24F")));
    }

    // -----------------------------
    // Equality cases
    // -----------------------------

    @Test
    void _FF0_is_equally_near_red_and_green() {
        assertEquals(Set.of("F00", "0F0"),
                asSet(NearestColor.nearestColorsOf("FF0")));
    }

    @Test
    void _0FF_is_equally_near_green_and_blue() {
        assertEquals(Set.of("0F0", "00F"),
                asSet(NearestColor.nearestColorsOf("0FF")));
    }

    @Test
    void _F0F_is_equally_near_red_and_blue() {
        assertEquals(Set.of("F00", "00F"),
                asSet(NearestColor.nearestColorsOf("F0F")));
    }

    @Test
    void _888_is_equally_near_all_three() {
        assertEquals(Set.of("F00", "0F0", "00F"),
                asSet(NearestColor.nearestColorsOf("888")));
    }

    @Test
    void _000_is_equally_near_all_three() {
        assertEquals(Set.of("F00", "0F0", "00F"),
                asSet(NearestColor.nearestColorsOf("000")));
    }

    @Test
    void _FFF_is_equally_near_all_three() {
        assertEquals(Set.of("F00", "0F0", "00F"),
                asSet(NearestColor.nearestColorsOf("FFF")));
    }

    // -----------------------------
    // Helper
    // -----------------------------

    private static Set<String> asSet(List<String> colors) {
        assertNotNull(colors);
        assertFalse(colors.isEmpty());
        return Set.copyOf(colors);
    }
}
