package io.treelevel.training.exercises;

/**
 * Exercise 2 — Palindrome Checker
 *
 * A palindrome reads the same forwards and backwards.
 * Rules:
 *   - Case-insensitive: "Racecar" is a palindrome
 *   - Ignore spaces:    "race car" is treated as "racecar"
 */
public class Exercise2_Palindrome {

    /**
     * Returns true if s is a palindrome (ignoring case and spaces).
     *
     * Hints:
     *   - Normalize with .toLowerCase() and .replace(" ", "")
     *   - Build the reversed string with a for loop (iterate from end to beginning)
     *   - Compare with .equals()
     */
    public static boolean isPalindrome(String s) {
        // TODO: implement palindrome check
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("racecar"));  // true
        System.out.println(isPalindrome("Racecar"));  // true
        System.out.println(isPalindrome("race car")); // true
        System.out.println(isPalindrome("hello"));    // false
        System.out.println(isPalindrome("level"));    // true
        System.out.println(isPalindrome("Training")); // false
    }
}
