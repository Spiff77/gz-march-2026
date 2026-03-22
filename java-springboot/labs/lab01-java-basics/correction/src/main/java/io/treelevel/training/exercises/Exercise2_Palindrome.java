package io.treelevel.training.exercises;

/**
 * Exercise 2 — Palindrome Checker (CORRECTION)
 */
public class Exercise2_Palindrome {

    public static boolean isPalindrome(String s) {
        // Normalize: lowercase, strip spaces
        String normalized = s.toLowerCase().replace(" ", "");

        // Build reversed string by iterating from end to beginning
        String reversed = "";
        for (int i = normalized.length() - 1; i >= 0; i--) {
            reversed += normalized.charAt(i);
        }

        return normalized.equals(reversed);
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
