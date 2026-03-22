package io.treelevel.training.ex4;

/**
 * Exercise 3 — Employee (CORRECTION)
 */
public class Employee {

    private final int    id;
    private final String name;
    private final double annualSalary;

    public Employee(int id, String name, double annualSalary) {
        this.id           = id;
        this.name         = name;
        this.annualSalary = annualSalary;
    }

    public int    getId()           { return id; }
    public String getName()         { return name; }
    public double getAnnualSalary() { return annualSalary; }

    public double monthlySalary() {
        return annualSalary / 12;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s — £%,.0f/yr | Monthly: £%,.2f",
                id, name, annualSalary, monthlySalary());
    }
}
