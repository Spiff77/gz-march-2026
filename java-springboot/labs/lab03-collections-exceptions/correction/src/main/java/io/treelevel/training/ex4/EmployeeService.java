package io.treelevel.training.ex4;

public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * Get employee by ID, or throw NotFoundException.
     * This is the standard Spring Boot service pattern.
     */
    public Employee getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
    }

    /**
     * Get employee by name (case-insensitive), or throw NotFoundException.
     */
    public Employee getByName(String name) {
        return repository.findByName(name)
            .orElseThrow(() -> new NotFoundException("Employee not found with name: " + name));
    }
}
