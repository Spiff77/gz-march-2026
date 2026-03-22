package io.treelevel.training.exercises;

/**
 * Exercise 4 — Temperature Converter (CORRECTION)
 */
public class Exercise4_TempConverter {

    public static double celsiusToFahrenheit(double celsius) {
        return Math.round(((celsius * 9.0 / 5.0) + 32) * 10.0) / 10.0;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return Math.round(((fahrenheit - 32) * 5.0 / 9.0) * 10.0) / 10.0;
    }

    public static void main(String[] args) {
        System.out.println(celsiusToFahrenheit(0.0));   // 32.0
        System.out.println(celsiusToFahrenheit(100.0)); // 212.0
        System.out.println(celsiusToFahrenheit(-40.0)); // -40.0

        System.out.println(fahrenheitToCelsius(32.0));  // 0.0
        System.out.println(fahrenheitToCelsius(98.6));  // 37.0
    }
}
