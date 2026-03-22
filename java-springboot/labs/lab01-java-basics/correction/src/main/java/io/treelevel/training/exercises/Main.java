package io.treelevel.training.exercises;

/**
 * Main entry point for Lab 01 correction.
 *
 * Expected output is shown in comments next to each call.
 */
public class Main {

    public static void main(String[] args) {

        // ── Exercise 1: FizzBuzz ──────────────────────────────────────────────
        System.out.println("=== Exercise 1: FizzBuzz (1 to 20) ===");
        for (int i = 1; i <= 20; i++) {
            System.out.println(Exercise1_FizzBuzz.fizzBuzz(i));
        }
        // Expected: 1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 16 17 Fizz 19 Buzz

        // ── Exercise 2: Palindrome ────────────────────────────────────────────
        System.out.println("\n=== Exercise 2: Palindrome ===");
        System.out.println(Exercise2_Palindrome.isPalindrome("racecar"));                     // true
        System.out.println(Exercise2_Palindrome.isPalindrome("Racecar"));                     // true
        System.out.println(Exercise2_Palindrome.isPalindrome("race car"));                    // true
        System.out.println(Exercise2_Palindrome.isPalindrome("hello"));                       // false
        System.out.println(Exercise2_Palindrome.isPalindrome("A man a plan a canal Panama")); // true
        System.out.println(Exercise2_Palindrome.isPalindrome("level"));                       // true
        System.out.println(Exercise2_Palindrome.isPalindrome("Training"));                    // false

        // ── Exercise 3: Word Count ────────────────────────────────────────────
        System.out.println("\n=== Exercise 3: Word Count ===");
        System.out.println(Exercise3_WordCount.countWords("The quick brown fox"));    // 4
        System.out.println(Exercise3_WordCount.countWords("  hello   world  "));      // 2
        System.out.println(Exercise3_WordCount.countWords(""));                        // 0
        System.out.println(Exercise3_WordCount.countWords("Java"));                    // 1

        // ── Exercise 4: Temperature Converter ────────────────────────────────
        System.out.println("\n=== Exercise 4: Temperature Converter ===");
        System.out.println(Exercise4_TempConverter.celsiusToFahrenheit(0.0));    // 32.0
        System.out.println(Exercise4_TempConverter.celsiusToFahrenheit(100.0));  // 212.0
        System.out.println(Exercise4_TempConverter.celsiusToFahrenheit(-40.0));  // -40.0
        System.out.println(Exercise4_TempConverter.fahrenheitToCelsius(32.0));   // 0.0
        System.out.println(Exercise4_TempConverter.fahrenheitToCelsius(98.6));   // 37.0

        // ── Exercise 5: Grade Calculator ─────────────────────────────────────
        System.out.println("\n=== Exercise 5: Grade Calculator ===");
        int[] aliceScores = {85, 92, 78, 95, 88};
        int[] bobScores   = {55, 60, 45, 72, 50};
        int[] evaScores   = {100, 98, 97, 99, 100};
        double aliceAvg = Exercise5_GradeCalc.calculateAverage(aliceScores);
        double bobAvg   = Exercise5_GradeCalc.calculateAverage(bobScores);
        double evaAvg   = Exercise5_GradeCalc.calculateAverage(evaScores);
        System.out.println("Alice: " + aliceAvg + " → " + Exercise5_GradeCalc.getLetterGrade(aliceAvg)); // 87.6 → B
        System.out.println("Bob:   " + bobAvg   + " → " + Exercise5_GradeCalc.getLetterGrade(bobAvg));   // 56.4 → F
        System.out.println("Eva:   " + evaAvg   + " → " + Exercise5_GradeCalc.getLetterGrade(evaAvg));   // 98.8 → A
    }
}
