package io.treelevel.training.ex4;

/**
 * Exercise 3 — Manager
 *
 * Extends Employee and adds bonus logic.
 *
 * Your tasks:
 *   1. Add a private field: bonusRate (double)
 *   2. Write the constructor: Manager(int id, String name, double annualSalary, double bonusRate)
 *      - Call super(id, name, annualSalary) first
 *   3. Implement calculateBonus() — returns getAnnualSalary() * bonusRate
 *   4. Override toString() to add the bonus to the parent's output
 */
public class Manager extends Employee {

    // TODO: add bonusRate field

    public Manager(int id, String name, double annualSalary, double bonusRate) {
        super(id, name, annualSalary);
        // TODO: save bonusRate
    }

    public double calculateBonus() {
        // TODO: return getAnnualSalary() * bonusRate
        return 0;
    }

    @Override
    public String toString() {
        // TODO: extend super.toString() to show the bonus
        return super.toString();
    }
}
