package io.treelevel.training.ex4;

/**
 * Exercise 3 — Manager (CORRECTION)
 */
public class Manager extends Employee {

    private final double bonusRate;

    public Manager(int id, String name, double annualSalary, double bonusRate) {
        super(id, name, annualSalary);
        this.bonusRate = bonusRate;
    }

    public double calculateBonus() {
        return getAnnualSalary() * bonusRate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Bonus: £%,.2f", calculateBonus());
    }
}
