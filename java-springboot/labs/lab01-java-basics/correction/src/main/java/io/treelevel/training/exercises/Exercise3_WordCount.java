package io.treelevel.training.exercises;

/**
 * Exercise 3 — Word Counter (CORRECTION)
 */
public class Exercise3_WordCount {

    public static int countWords(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return 0;
        }
        String[] words = sentence.trim().split("\\s+");
        return words.length;
    }

    public static void main(String[] args) {
        System.out.println(countWords("The quick brown fox")); // 4
        System.out.println(countWords("  hello   world  "));   // 2
        System.out.println(countWords(""));                     // 0
        System.out.println(countWords("Java"));                 // 1
    }
}
