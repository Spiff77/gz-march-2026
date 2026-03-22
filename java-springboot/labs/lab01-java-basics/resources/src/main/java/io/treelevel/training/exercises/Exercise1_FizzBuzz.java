package io.treelevel.training.exercises;

/**
 * Exercise 1 — FizzBuzz
 *
 * Print numbers from 1 to n, but:
 *   - print "Fizz"     for multiples of 3
 *   - print "Buzz"     for multiples of 5
 *   - print "FizzBuzz" for multiples of both 3 and 5
 */
public class Exercise1_FizzBuzz {

    /**
     * Returns the FizzBuzz value for a single number n.
     * - If n is divisible by 15 → "FizzBuzz"
     * - If n is divisible by 3  → "Fizz"
     * - If n is divisible by 5  → "Buzz"
     * - Otherwise               → String.valueOf(n)
     */
    public static String fizzBuzz(int n) {
        // TODO: implement FizzBuzz logic
        return "";
    }

    public static void main(String[] args) {
        // Print FizzBuzz for numbers 1 through 50
        for (int i = 1; i <= 50; i++) {
            System.out.println(fizzBuzz(i));
        }
    }
}
