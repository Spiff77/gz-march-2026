package io.treelevel.training.exercises;

/**
 * Exercise 1 — FizzBuzz (CORRECTION)
 */
public class Exercise1_FizzBuzz {

    public static String fizzBuzz(int n) {
        if (n % 15 == 0) {
            return "FizzBuzz";
        } else if (n % 3 == 0) {
            return "Fizz";
        } else if (n % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(n);
        }
    }

    public static void main(String[] args) {
        // Expected output (first 20):
        // 1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz,
        // 11, Fizz, 13, 14, FizzBuzz, 16, 17, Fizz, 19, Buzz
        for (int i = 1; i <= 50; i++) {
            System.out.println(fizzBuzz(i));
        }
    }
}
