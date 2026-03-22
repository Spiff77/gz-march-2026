package io.treelevel.training.generics;

import java.util.List;
import java.util.Optional;

/**
 * Run this class to test your exercises.
 * Uncomment each section as you complete the corresponding exercise.
 */
public class Main {

    public static void main(String[] args) {

        // --- Exercise 1: Generic Box ---
        // Uncomment after making Box generic:
        //
        // Box<String> nameBox = new Box<>("Alice");
        // System.out.println("String box: " + nameBox.getContent());
        //
        // Box<Integer> ageBox = new Box<>(25);
        // System.out.println("Integer box: " + ageBox.getContent());
        //
        // Box<Employee> empBox = new Box<>(new Employee(1L, "Bob"));
        // System.out.println("Employee box: " + empBox.getContent());

        // --- Exercise 2: Generic Pair ---
        // Uncomment after making Pair generic:
        //
        // Pair<String, Integer> grade = new Pair<>("Alice", 95);
        // String student = grade.getFirst();     // no cast needed
        // Integer score = grade.getSecond();     // no cast needed
        // System.out.println(student + " scored " + score);
        //
        // Pair<Employee, String> assignment = new Pair<>(new Employee(1L, "Bob"), "Engineering");
        // System.out.println(assignment.getFirst().getName() + " → " + assignment.getSecond());

        // --- Exercise 3: Generic Methods ---
        // Uncomment after making ListUtils methods generic:
        //
        // List<String> names = List.of("Alice", "Bob", "Charlie", "Diana");
        // String first = ListUtils.findFirst(names);
        // String last = ListUtils.findLast(names);
        // List<String> evens = ListUtils.everyOther(names);
        // System.out.println("First: " + first);
        // System.out.println("Last: " + last);
        // System.out.println("Every other: " + evens);
        //
        // List<Integer> numbers = List.of(10, 20, 30, 40, 50);
        // Integer firstNum = ListUtils.findFirst(numbers);
        // System.out.println("First number: " + firstNum);

        // --- Exercise 4: Generic Repository ---
        // Uncomment after implementing EmployeeRepository:
        //
        // EmployeeRepository repo = new EmployeeRepository();
        // repo.save(new Employee(null, "Alice"));
        // repo.save(new Employee(null, "Bob"));
        // repo.save(new Employee(null, "Charlie"));
        //
        // System.out.println("All: " + repo.findAll());
        //
        // Optional<Employee> found = repo.findById(1L);
        // System.out.println("Found: " + found.orElse(null));
        //
        // Optional<Employee> missing = repo.findById(99L);
        // System.out.println("Missing: " + missing.orElse(null));
        //
        // repo.deleteById(2L);
        // System.out.println("After delete: " + repo.findAll());
    }
}
