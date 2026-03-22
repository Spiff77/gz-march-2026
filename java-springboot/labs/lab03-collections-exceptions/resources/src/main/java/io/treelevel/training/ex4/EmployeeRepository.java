package io.treelevel.training.ex4;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EmployeeRepository {

    // Simulated in-memory "database"
    private final Map<Long, Employee> database = new HashMap<>();

    public EmployeeRepository() {
        database.put(1L, new Employee(1L, "Alice Chen",   "Engineering", 85000));
        database.put(2L, new Employee(2L, "Bob Smith",    "HR",          62000));
        database.put(3L, new Employee(3L, "Carol White",  "Engineering", 91000));
        database.put(4L, new Employee(4L, "David Kumar",  "Finance",     74000));
    }

    /**
     * Find an employee by ID.
     * Returns Optional.empty() if no employee with that ID exists.
     * This is exactly how JpaRepository.findById() works in Spring Data.
     */
    public Optional<Employee> findById(Long id) {
        // TODO: look up in the database map
        // Return Optional.of(employee) if found
        // Return Optional.empty() if not found
        // Hint: use database.containsKey(id) or Optional.ofNullable(database.get(id))
        return Optional.empty();
    }

    /**
     * Find an employee by name (case-insensitive).
     * Returns Optional.empty() if not found.
     */
    public Optional<Employee> findByName(String name) {
        // TODO: iterate over database.values()
        // Return Optional.of(employee) if employee.getName().equalsIgnoreCase(name)
        // Return Optional.empty() if no match
        return Optional.empty();
    }

    public static void main(String[] args) {
        EmployeeRepository repo    = new EmployeeRepository();
        EmployeeService     service = new EmployeeService(repo);

        // Successful lookups
        Employee alice = service.getById(1L);
        System.out.println("Found: " + alice);

        Employee carol = service.getByName("carol white");  // case-insensitive
        System.out.println("Found: " + carol);

        // Optional methods — practice with the raw Optional
        Optional<Employee> maybeEmp = repo.findById(99L);

        System.out.println("isPresent: " + maybeEmp.isPresent());  // false
        System.out.println("isEmpty:   " + maybeEmp.isEmpty());    // true

        // orElse — provide a default
        Employee fallback = maybeEmp.orElse(new Employee(0L, "Unknown", "N/A", 0));
        System.out.println("orElse:    " + fallback);

        // orElseThrow — throw if empty
        try {
            service.getById(99L);
        } catch (NotFoundException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
