package io.treelevel.training.exercises;

/**
 * Exercise 4 — Temperature Converter
 *
 * Conversion formulas:
 *   C to F: (C × 9/5) + 32
 *   F to C: (F − 32) × 5/9
 */
public class Exercise4_TempConverter {

    /**
     * Converts Celsius to Fahrenheit.
     * Hint: use 9.0/5 (not 9/5) to avoid integer division.
     */
    public static double celsiusToFahrenheit(double celsius) {
        // TODO: implement conversion
        return 0;
    }

    /**
     * Converts Fahrenheit to Celsius.
     * Hint: use 5.0/9 (not 5/9) to avoid integer division.
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        // TODO: implement conversion
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(celsiusToFahrenheit(0.0));   // 32.0
        System.out.println(celsiusToFahrenheit(100.0)); // 212.0
        System.out.println(celsiusToFahrenheit(-40.0)); // -40.0

        System.out.println(fahrenheitToCelsius(32.0));  // 0.0
        System.out.println(fahrenheitToCelsius(98.6));  // 37.0
    }
}
