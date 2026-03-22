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
        // TODO: call repository.findById(id)
        //       chain .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id))
        return null;
    }

    /**
     * Get employee by name (case-insensitive), or throw NotFoundException.
     */
    public Employee getByName(String name) {
        // TODO: same pattern as getById but using findByName
        return null;
    }
}
