package io.treelevel.training;

import io.treelevel.training.ex1.BankAccount;
import io.treelevel.training.ex2.Animal;
import io.treelevel.training.ex2.Bird;
import io.treelevel.training.ex2.Cat;
import io.treelevel.training.ex2.Dog;
import io.treelevel.training.ex4.Employee;
import io.treelevel.training.ex4.Manager;

/**
 * Main entry point for Lab 02 — OOP.
 *
 * Run this class to exercise all exercises at once.
 * TODO methods will produce empty/default output until implemented.
 */
public class Main {

    public static void main(String[] args) {

        // ── Exercise 1: BankAccount ───────────────────────────────────────────
        System.out.println("=== Exercise 1: BankAccount ===");
        BankAccount alice = new BankAccount("Alice");

        System.out.println(alice);
        alice.deposit(2000.0);
        alice.withdraw(300.0);
        alice.withdraw(5000.0);   // ERROR: insufficient funds
        alice.deposit(-50.0);     // ERROR: negative amount

        // ── Exercise 2: Animal interface ──────────────────────────────────────
        System.out.println("\n=== Exercise 2: Animal Interface ===");
        Dog  dog  = new Dog("Rex", "Labrador");
        Cat  cat  = new Cat("Whiskers", true);
        Bird bird = new Bird("Tweety", 35.5);

        System.out.println(dog.describe());
        System.out.println(cat.describe());
        System.out.println(bird.describe());

        System.out.println("\n--- All animals speaking ---");
        Animal[] animals = {dog, cat, bird};
        for (Animal animal : animals) {
            System.out.println(animal.getName() + ": " + animal.speak());
        }

        System.out.println("\n--- Dog details ---");
        System.out.println("Name: "   + dog.getName());
        System.out.println("Breed: "  + dog.getBreed());

        System.out.println("\n--- Bird details ---");
        System.out.println("Name: " + bird.getName());
        System.out.printf("Wingspan: %.1f cm%n", bird.getWingspan());

        // ── Exercise 3: Employee / Manager ───────────────────────────────────
        System.out.println("\n=== Exercise 3: Employee & Manager ===");
        Employee james = new Employee(2, "James Liu",  65000.0);
        Employee aisha = new Employee(3, "Aisha Patel", 68000.0);
        Manager  sarah = new Manager(1, "Sarah Chen", 120000.0, 0.20);

        Employee[] allStaff = {sarah, james, aisha};
        for (Employee emp : allStaff) {
            System.out.println(emp);
            if (emp instanceof Manager) {
                Manager mgr = (Manager) emp;
                System.out.printf("  Bonus: £%,.2f%n", mgr.calculateBonus());
            }
        }
    }
}
