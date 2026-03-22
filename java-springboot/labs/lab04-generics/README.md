# Lab 4 — Generics

> **Corresponding chapter:** Chapter 4 — Generics
>
> **Time estimate:** 45 minutes
>
> **Prerequisites:** Chapters 1–4. You should have completed Lab 3.

---

## Overview

Four exercises that progress from making a simple class generic, to implementing a generic interface that mirrors the pattern Spring Data uses. All exercises are in the `io.treelevel.training.generics` package.

Run `Main.java` to test your work — uncomment each section as you complete the corresponding exercise.

---

## Exercise 1 — Generic Box (10 min)

The `Box` class currently uses `Object` — it compiles but requires casts and loses type safety.

**Your task:** Make `Box` generic.

1. Add a type parameter `<T>` to the class declaration.
2. Replace every `Object` with `T` (field, constructor, getter, setter).

### Before

```java
Box box = new Box("Alice");
Object content = box.getContent();   // returns Object — need to cast
String name = (String) content;       // unsafe cast
```

### After

```java
Box<String> box = new Box<>("Alice");
String name = box.getContent();       // returns String — no cast needed

Box<Integer> ageBox = new Box<>(25);
int age = ageBox.getContent();        // returns Integer — type safe
```

<details>
<summary>Hint</summary>

```java
public class Box<T> {
    private T content;
    // ...
}
```

</details>

---

## Exercise 2 — Generic Pair (10 min)

The `Pair` class holds two values but stores them as `Object`. Make it generic with two type parameters.

**Your task:**

1. Add type parameters `<A, B>` to the class declaration.
2. Replace `Object` with `A` for the first element and `B` for the second.

### After

```java
Pair<String, Integer> grade = new Pair<>("Alice", 95);
String student = grade.getFirst();    // String — no cast
Integer score = grade.getSecond();    // Integer — no cast
```

<details>
<summary>Hint</summary>

```java
public class Pair<A, B> {
    private final A first;
    private final B second;
    // ...
}
```

</details>

---

## Exercise 3 — Generic Methods (10 min)

`ListUtils` has three static methods that use raw `List` and return `Object`. Make them generic.

**Your task:** For each method:

1. Add `<T>` before the return type.
2. Replace `List` with `List<T>` and `Object` with `T`.

### Methods to fix

| Method | Purpose |
|---|---|
| `findFirst(List)` | Returns the first element, or null if empty |
| `findLast(List)` | Returns the last element, or null if empty |
| `everyOther(List)` | Returns elements at even indices (0, 2, 4, ...) |

### After

```java
List<String> names = List.of("Alice", "Bob", "Charlie");
String first = ListUtils.findFirst(names);        // "Alice" — no cast
List<String> evens = ListUtils.everyOther(names);  // ["Alice", "Charlie"] — typed
```

<details>
<summary>Hint</summary>

```java
public static <T> T findFirst(List<T> list) {
    // ...
}
```

</details>

---

## Exercise 4 — Generic Repository Interface (15 min)

This is the most important exercise — it mirrors the pattern you'll use with Spring Data.

The `Repository` interface currently uses `Object` and `Long`. The `EmployeeRepository` class needs to implement it.

**Step 1:** Make `Repository` generic.

1. Add type parameters `<T, ID>` to the interface.
2. Replace `Object` with `T` and `Long` with `ID`.

**Step 2:** Implement the interface in `EmployeeRepository`.

1. Add `implements Repository<Employee, Long>` to the class declaration.
2. Implement all four methods using the `storage` map provided.

### Specifications

| Method | Implementation |
|---|---|
| `findById(Long id)` | Return `Optional.ofNullable(storage.get(id))` |
| `findAll()` | Return `new ArrayList<>(storage.values())` |
| `save(Employee entity)` | If id is null, assign `nextId++`. Put in storage. Return entity. |
| `deleteById(Long id)` | Remove from storage. |

### Why this matters

When you later write:

```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> { }
```

Spring Data does exactly what you just did — but generates the implementation automatically. You've built the pattern by hand.

<details>
<summary>Solution — Repository interface</summary>

```java
public interface Repository<T, ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    T save(T entity);
    void deleteById(ID id);
}
```

</details>

<details>
<summary>Solution — EmployeeRepository</summary>

```java
public class EmployeeRepository implements Repository<Employee, Long> {

    private final Map<Long, Employee> storage = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Employee save(Employee entity) {
        if (entity.getId() == null) {
            entity.setId(nextId++);
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
```

</details>

---

## Completion checklist

- [ ] Exercise 1: `Box<T>` compiles; `Box<String>` and `Box<Integer>` work without casts
- [ ] Exercise 2: `Pair<A, B>` compiles; `getFirst()` and `getSecond()` return the correct types
- [ ] Exercise 3: All three `ListUtils` methods are generic; no raw types, no casts
- [ ] Exercise 4: `Repository<T, ID>` interface is generic; `EmployeeRepository` implements it and all four methods work

**Coming up next:** Maven & Spring Boot setup — you'll see `JpaRepository<Employee, Long>` and recognize the exact same pattern.
