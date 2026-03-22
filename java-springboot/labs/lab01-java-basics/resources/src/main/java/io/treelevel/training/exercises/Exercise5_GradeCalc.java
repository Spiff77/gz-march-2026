package io.treelevel.training.exercises;

/**
 * Exercise 5 — Grade Calculator
 *
 * Grading scale:
 *   90 – 100 → A
 *   80 – 89  → B
 *   70 – 79  → C
 *   60 – 69  → D
 *    0 – 59  → F
 */
public class Exercise5_GradeCalc {

    /**
     * Returns the average of the scores array as a double.
     * Hint: sum all elements in a for-each loop, then divide by scores.length.
     */
    public static double calculateAverage(int[] scores) {
        // TODO: compute and return the average
        return 0;
    }

    /**
     * Returns the letter grade ("A", "B", "C", "D", or "F") for the given average.
     * Hint: use if / else if chains.
     */
    public static String getLetterGrade(double average) {
        // TODO: apply the grading scale
        return "";
    }

    public static void main(String[] args) {
        int[] aliceScores = {85, 92, 78, 95, 88};
        int[] bobScores   = {55, 60, 45, 72, 50};
        int[] evaScores   = {100, 98, 97, 99, 100};

        System.out.println("Alice: " + getLetterGrade(calculateAverage(aliceScores))); // B
        System.out.println("Bob:   " + getLetterGrade(calculateAverage(bobScores)));   // F
        System.out.println("Eva:   " + getLetterGrade(calculateAverage(evaScores)));   // A
    }
}
