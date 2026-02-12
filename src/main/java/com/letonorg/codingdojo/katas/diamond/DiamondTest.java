package com.letonorg.codingdojo.katas.diamond;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiamondTest {

    @Test
    void should_return_empty_string_for_invalid_input_below_A() {
        assertEquals("", Diamond.makeDiamond('@'));
    }

    @Test
    void should_return_empty_string_for_invalid_input_above_Z() {
        assertEquals("", Diamond.makeDiamond('['));
    }

    @Test
    void should_create_diamond_for_A() {
        assertEquals("A", normalize(Diamond.makeDiamond('A')));
    }

    @Test
    void should_create_diamond_for_B() {
        String expected = """
                A
               B B
                A
                """;
        assertEquals(normalize(expected), normalize(Diamond.makeDiamond('B')));
    }

    @Test
    void should_create_diamond_for_C() {
        String expected = """
                 A
                B B
               C   C
                B B
                 A
                """;
        assertEquals(normalize(expected), normalize(Diamond.makeDiamond('C')));
    }

    @Test
    void should_create_diamond_for_D() {
        String expected = """
                  A
                 B B
                C   C
               D     D
                C   C
                 B B
                  A
                """;
        assertEquals(normalize(expected), normalize(Diamond.makeDiamond('D')));
    }

    @Test
    void should_have_correct_number_of_lines() {
        // For letter N: lines = 2*(N-'A') + 1
        assertEquals(1, linesOf(Diamond.makeDiamond('A')));
        assertEquals(3, linesOf(Diamond.makeDiamond('B')));
        assertEquals(5, linesOf(Diamond.makeDiamond('C')));
        assertEquals(7, linesOf(Diamond.makeDiamond('D')));
        assertEquals(51, linesOf(Diamond.makeDiamond('Z')));
    }

    @Test
    void should_be_vertically_symmetric() {
        String diamond = Diamond.makeDiamond('F');
        String[] lines = diamond.split("\n");
        for (int i = 0; i < lines.length / 2; i++) {
            assertEquals(lines[i], lines[lines.length - 1 - i]);
        }
    }

    @Test
    void should_be_horizontally_symmetric_on_each_line() {
        String diamond = Diamond.makeDiamond('G');
        for (String line : diamond.split("\n")) {
            assertEquals(line, new StringBuilder(line).reverse().toString());
        }
    }

    @Test
    void should_have_A_only_on_first_and_last_line() {
        String diamond = normalize(Diamond.makeDiamond('E'));
        String[] lines = diamond.split("\n");

        assertEquals("A", lines[0].trim());
        assertEquals("A", lines[lines.length - 1].trim());

        for (int i = 1; i < lines.length - 1; i++) {
            assertFalse(lines[i].contains("A"));
        }
    }

    @Test
    void should_have_each_letter_twice_except_A() {
        String diamond = normalize(Diamond.makeDiamond('H'));
        // A appears exactly twice (top and bottom),
        // others appear 4 times (two per occurrence line * 2 symmetrical lines)
        assertEquals(2, countChar(diamond, 'A'));
        assertEquals(4, countChar(diamond, 'B'));
        assertEquals(4, countChar(diamond, 'C'));
        assertEquals(4, countChar(diamond, 'D'));
        assertEquals(4, countChar(diamond, 'E'));
        assertEquals(4, countChar(diamond, 'F'));
        assertEquals(4, countChar(diamond, 'G'));
        assertEquals(2, countChar(diamond, 'H')); // middle line has two H, only once
    }

    // ----------------- helpers -----------------

    /**
     * Normalizes:
     * - Windows/Mac line endings to \n
     * - trims trailing spaces on each line
     * - trims leading/trailing blank lines
     */
    private static String normalize(String s) {
        if (s == null) return "";
        String unified = s.replace("\r\n", "\n").replace("\r", "\n");
        String[] rawLines = unified.split("\n", -1);

        StringBuilder out = new StringBuilder();
        for (String line : rawLines) {
            out.append(rtrim(line)).append("\n");
        }

        String trimmed = out.toString().trim();
        return trimmed.isEmpty() ? "" : trimmed;
    }

    private static String rtrim(String s) {
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') i--;
        return s.substring(0, i + 1);
    }

    private static int linesOf(String diamond) {
        String n = normalize(diamond);
        return n.isEmpty() ? 0 : n.split("\n").length;
    }

    private static int countChar(String s, char c) {
        int count = 0;
        for (char x : s.toCharArray()) {
            if (x == c) count++;
        }
        return count;
    }
}
