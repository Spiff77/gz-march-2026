package io.treelevel.training.exercises;

/**
 * Main entry point for Lab 01 — Java Basics.
 *
 * Run this class to verify all 5 exercises at once.
 * Each exercise method is called with sample inputs.
 * Until the methods are implemented the output will show default/empty values.
 */
public class Main {

    public static void main(String[] args) {

        // ── Exercise 1: FizzBuzz ──────────────────────────────────────────────
        System.out.println("=== Exercise 1: FizzBuzz (1 to 20) ===");
        for (int i = 1; i <= 20; i++) {
            System.out.println(Exercise1_FizzBuzz.fizzBuzz(i));
        }

        // ── Exercise 2: Palindrome ────────────────────────────────────────────
        System.out.println("\n=== Exercise 2: Palindrome ===");
        System.out.println(Exercise2_Palindrome.isPalindrome("racecar"));                     // true
        System.out.println(Exercise2_Palindrome.isPalindrome("Racecar"));                     // true
        System.out.println(Exercise2_Palindrome.isPalindrome("race car"));                    // true
        System.out.println(Exercise2_Palindrome.isPalindrome("hello"));                       // false
        System.out.println(Exercise2_Palindrome.isPalindrome("A man a plan a canal Panama")); // true

        // ── Exercise 3: Word Count ────────────────────────────────────────────
        System.out.println("\n=== Exercise 3: Word Count ===");
        System.out.println(Exercise3_WordCount.countWords("The quick brown fox"));    // 4
        System.out.println(Exercise3_WordCount.countWords("  hello   world  "));      // 2
        System.out.println(Exercise3_WordCount.countWords(""));                        // 0

        // ── Exercise 4: Temperature Converter ────────────────────────────────
        System.out.println("\n=== Exercise 4: Temperature Converter ===");
        System.out.println(Exercise4_TempConverter.celsiusToFahrenheit(0.0));    // 32.0
        System.out.println(Exercise4_TempConverter.celsiusToFahrenheit(100.0));  // 212.0
        System.out.println(Exercise4_TempConverter.fahrenheitToCelsius(98.6));   // 37.0

        // ── Exercise 5: Grade Calculator ─────────────────────────────────────
        System.out.println("\n=== Exercise 5: Grade Calculator ===");
        int[] aliceScores = {85, 92, 78, 95, 88};
        int[] bobScores   = {55, 60, 45, 72, 50};
        double aliceAvg = Exercise5_GradeCalc.calculateAverage(aliceScores);
        double bobAvg   = Exercise5_GradeCalc.calculateAverage(bobScores);
        System.out.println("Alice: " + aliceAvg + " → " + Exercise5_GradeCalc.getLetterGrade(aliceAvg)); // 87.6 → B
        System.out.println("Bob:   " + bobAvg   + " → " + Exercise5_GradeCalc.getLetterGrade(bobAvg));   // 56.4 → F
    }
}
