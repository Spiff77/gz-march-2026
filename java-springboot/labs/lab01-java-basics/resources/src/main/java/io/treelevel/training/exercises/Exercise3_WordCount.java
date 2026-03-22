package io.treelevel.training.exercises;

/**
 * Exercise 3 — Word Counter
 *
 * Count the number of words in a sentence.
 * Words are separated by one or more spaces.
 */
public class Exercise3_WordCount {

    /**
     * Returns the number of words in sentence.
     * Returns 0 for null or blank input.
     *
     * Hints:
     *   - sentence.trim().isEmpty() — checks if blank
     *   - sentence.trim().split("\\s+") — splits on one or more spaces
     */
    public static int countWords(String sentence) {
        // TODO: implement word count
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(countWords("The quick brown fox")); // 4
        System.out.println(countWords("  hello   world  "));   // 2
        System.out.println(countWords(""));                     // 0
        System.out.println(countWords("Java"));                 // 1
    }
}
