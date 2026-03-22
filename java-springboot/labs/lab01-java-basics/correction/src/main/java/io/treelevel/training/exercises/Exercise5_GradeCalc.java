package io.treelevel.training.exercises;

/**
 * Exercise 5 — Grade Calculator (CORRECTION)
 */
public class Exercise5_GradeCalc {

    public static double calculateAverage(int[] scores) {
        double sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum / scores.length;
    }

    public static String getLetterGrade(double average) {
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        return "F";
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
