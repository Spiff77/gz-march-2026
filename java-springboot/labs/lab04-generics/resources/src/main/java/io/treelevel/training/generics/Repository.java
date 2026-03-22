package io.treelevel.training.generics;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 4 — Generic Interface
 *
 * This interface defines a generic data-access pattern: store, retrieve, and delete
 * entities of any type, identified by any key type.
 *
 * TODO: Add two type parameters <T, ID> to this interface.
 *       T = the entity type, ID = the identifier type.
 *       Replace Object and Long with the appropriate type parameters.
 */
public interface Repository {

    Optional<Object> findById(Long id);

    List<Object> findAll();

    Object save(Object entity);

    void deleteById(Long id);
}
