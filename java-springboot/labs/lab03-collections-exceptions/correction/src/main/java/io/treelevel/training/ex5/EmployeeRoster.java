package io.treelevel.training.ex5;

import io.treelevel.training.ex4.Employee;
import io.treelevel.training.ex4.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRoster {

    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        employees.add(employee);
    }

    /**
     * Return a list of all employees in the given department.
     * Return an empty list if no employees match.
     * Uses an explicit for-loop — no streams.
     */
    public List<Employee> getByDepartment(String department) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getDepartment().equalsIgnoreCase(department)) {
                result.add(e);
            }
        }
        return result;
    }

    /**
     * Return the highest-paid employee in the given department.
     * Throw NotFoundException if the department has no employees.
     */
    public Employee getHighestPaid(String department) {
        List<Employee> inDept = getByDepartment(department);

        if (inDept.isEmpty()) {
            throw new NotFoundException("No employees in department: " + department);
        }

        Employee best = inDept.get(0);
        for (int i = 1; i < inDept.size(); i++) {
            if (inDept.get(i).getSalary() > best.getSalary()) {
                best = inDept.get(i);
            }
        }
        return best;
    }

    /**
     * Return the total number of employees.
     */
    public int totalCount() {
        return employees.size();
    }

    /**
     * Print all employees grouped by department.
     * Format:
     *   Engineering (3):
     *     - Alice Chen
     *     - Carol White
     *     ...
     */
    public void printRoster() {
        Map<String, List<Employee>> byDept = new HashMap<>();

        for (Employee e : employees) {
            String dept = e.getDepartment();
            if (!byDept.containsKey(dept)) {
                byDept.put(dept, new ArrayList<>());
            }
            byDept.get(dept).add(e);
        }

        for (Map.Entry<String, List<Employee>> entry : byDept.entrySet()) {
            System.out.println(entry.getKey() + " (" + entry.getValue().size() + "):");
            for (Employee e : entry.getValue()) {
                System.out.println("  - " + e.getName());
            }
        }
    }

    public static void main(String[] args) {
        EmployeeRoster roster = new EmployeeRoster();

        roster.add(new Employee(1L, "Alice Chen",    "Engineering", 85000));
        roster.add(new Employee(2L, "Bob Smith",     "HR",          62000));
        roster.add(new Employee(3L, "Carol White",   "Engineering", 91000));
        roster.add(new Employee(4L, "David Kumar",   "Finance",     74000));
        roster.add(new Employee(5L, "Emma Wilson",   "Engineering", 78000));
        roster.add(new Employee(6L, "Frank Zhang",   "HR",          65000));
        roster.add(new Employee(7L, "Grace Okonkwo", "Finance",     80000));

        System.out.println("Total employees: " + roster.totalCount());  // 7

        // Filter by department
        List<Employee> engineers = roster.getByDepartment("Engineering");
        System.out.println("\nEngineering team (" + engineers.size() + "):");
        for (Employee e : engineers) {
            System.out.println("  - " + e.getName() + " (\u00a3" + e.getSalary() + ")");
        }

        // Highest paid
        Employee topEng = roster.getHighestPaid("Engineering");
        System.out.println("\nHighest paid in Engineering: " + topEng.getName()); // Carol White

        Employee topFin = roster.getHighestPaid("Finance");
        System.out.println("Highest paid in Finance: " + topFin.getName()); // Grace Okonkwo

        // Exception case
        try {
            roster.getHighestPaid("Legal");
        } catch (NotFoundException e) {
            System.out.println("\nCaught: " + e.getMessage());
        }

        // Print the full roster
        System.out.println();
        roster.printRoster();
    }
}
