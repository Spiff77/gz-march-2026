package io.treelevel.training.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Exercise 4 — Implement the generic Repository interface
 *
 * TODO: Make this class implement Repository<Employee, Long>
 *       Then implement all four methods using the storage map below.
 */
public class EmployeeRepository {

    private final Map<Long, Employee> storage = new HashMap<>();
    private Long nextId = 1L;

    // TODO: implement findById — return Optional.ofNullable(storage.get(id))

    // TODO: implement findAll — return new ArrayList<>(storage.values())

    // TODO: implement save — assign an id if null, put in storage, return the entity

    // TODO: implement deleteById — remove from storage
}
