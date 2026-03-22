# Chapter 4 — Generics

> **Goal:** Understand Java generics well enough to read and write Spring Boot code confidently. By the end, signatures like `List<Employee>`, `Optional<Employee>`, and `JpaRepository<Employee, Long>` will make complete sense.

---

## 4.1 Why Generics?

Before generics (Java 1.4 and earlier), collections stored raw `Object` references. This meant no type checking at compile time:

```java
// Without generics — dangerous
List names = new ArrayList();
names.add("Alice");
names.add(42);           // compiles fine — no type check
String name = (String) names.get(1);  // ClassCastException at runtime!
```

Generics add **compile-time type safety** — the compiler catches type errors before you run the code:

```java
// With generics — safe
List<String> names = new ArrayList<>();
names.add("Alice");
names.add(42);           // COMPILE ERROR — caught immediately
String name = names.get(0);  // no cast needed
```

> **Rule:** Always use typed collections. Never use raw `List`, `Map`, or `Set`.

---

## 4.2 The Diamond Syntax `<>`

The angle brackets `<>` specify what type a generic class holds. On the right side of `=`, Java can infer the type — this is the **diamond operator**:

```java
// Full syntax
List<String> names = new ArrayList<String>();

// Diamond operator — compiler infers the type from the left side
List<String> names = new ArrayList<>();

// Map with two type parameters
Map<String, Integer> scores = new HashMap<>();
```

You already used this in Chapter 3. Now let's understand what's happening behind the `<>`.

---

## 4.3 Generic Classes

A generic class declares one or more **type parameters** in angle brackets. The convention is single uppercase letters:

| Parameter | Convention |
|---|---|
| `T` | Type (general) |
| `E` | Element (collections) |
| `K`, `V` | Key, Value (maps) |
| `N` | Number |

### Writing a generic class

```java
public class Box<T> {

    private T content;

    public Box(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
```

Using it:

```java
Box<String> nameBox = new Box<>("Alice");
String name = nameBox.getContent();   // returns "Alice" — no cast

Box<Integer> ageBox = new Box<>(25);
int age = ageBox.getContent();        // returns 25

Box<Employee> empBox = new Box<>(new Employee("Bob"));
Employee emp = empBox.getContent();   // returns the Employee
```

One class, any type. The compiler enforces type safety for each usage.

### Multiple type parameters

```java
public class Pair<A, B> {

    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst()  { return first; }
    public B getSecond() { return second; }
}
```

```java
Pair<String, Integer> entry = new Pair<>("Alice", 95);
String name = entry.getFirst();   // "Alice"
int score = entry.getSecond();    // 95

Pair<Employee, Department> assignment = new Pair<>(emp, dept);
```

---

## 4.4 Generic Methods

A method can declare its own type parameter, independent of the class:

```java
public class ListUtils {

    // <T> before the return type declares the type parameter
    public static <T> T findFirst(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
```

```java
List<String> names = List.of("Alice", "Bob");
String first = ListUtils.findFirst(names);   // "Alice" — compiler infers T = String

List<Integer> numbers = List.of(10, 20, 30);
Integer first = ListUtils.findFirst(numbers);  // 10 — compiler infers T = Integer
```

The compiler infers `T` from the argument you pass. No need to specify it explicitly.

---

## 4.5 Bounded Type Parameters

Sometimes you need to restrict what types are allowed. Use `extends` to set an upper bound:

```java
// T must be a Number (Integer, Double, Long, etc.)
public static <T extends Number> double sum(List<T> numbers) {
    double total = 0;
    for (T n : numbers) {
        total += n.doubleValue();  // doubleValue() is available because T extends Number
    }
    return total;
}
```

```java
sum(List.of(1, 2, 3));       // works — Integer extends Number → 6.0
sum(List.of(1.5, 2.5));      // works — Double extends Number → 4.0
sum(List.of("a", "b"));      // COMPILE ERROR — String does not extend Number
```

This is also how `Comparable` works — you'll see this pattern in sorting:

```java
// T must implement Comparable<T>
public static <T extends Comparable<T>> T findMax(List<T> list) {
    T max = list.get(0);
    for (T item : list) {
        if (item.compareTo(max) > 0) {
            max = item;
        }
    }
    return max;
}
```

```java
findMax(List.of(3, 1, 4, 1, 5));         // 5
findMax(List.of("banana", "apple"));      // "banana"
```

---

## 4.6 Wildcards `?`

The wildcard `?` means "unknown type". You'll encounter it when reading library code:

```java
// ? — any type
public void printAll(List<?> items) {
    for (Object item : items) {
        System.out.println(item);
    }
}

// ? extends Number — any subclass of Number
public double sumAll(List<? extends Number> numbers) {
    double total = 0;
    for (Number n : numbers) {
        total += n.doubleValue();
    }
    return total;
}
```

The key difference from bounded type parameters: wildcards are used when you **consume** a collection but don't need to name the type. You'll mostly **read** wildcard signatures, not write them.

---

## 4.7 Generic Interfaces

Interfaces can be generic too. This is the pattern Spring Data uses:

```java
// A generic interface for any repository
public interface Repository<T, ID> {

    T findById(ID id);
    List<T> findAll();
    T save(T entity);
    void deleteById(ID id);
}
```

Implementing it for a specific type:

```java
public class EmployeeRepository implements Repository<Employee, Long> {

    @Override
    public Employee findById(Long id) {
        // implementation
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Employee save(Employee entity) {
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        // implementation
    }
}
```

When the class says `implements Repository<Employee, Long>`:
- Every `T` in the interface becomes `Employee`
- Every `ID` becomes `Long`

> This is exactly how Spring Data works. When you write `JpaRepository<Employee, Long>`, Spring generates the implementation automatically using this same pattern.

---

## 4.8 Reading Generic Signatures

You will see these patterns throughout the Spring Boot course. Practice reading them:

```java
List<Employee>                 // a list of Employee objects
Map<String, List<Employee>>    // a map: department name → list of employees
Optional<Employee>             // either an Employee or nothing (avoids null)
```

`Optional<T>` is Java's way of representing "this might be empty":

```java
Optional<String> maybeName = Optional.of("Alice");
Optional<String> empty = Optional.empty();

// Safe access — no NullPointerException
String name = maybeName.orElse("Unknown");           // "Alice"
String fallback = empty.orElse("Unknown");            // "Unknown"

// Check before using
if (maybeName.isPresent()) {
    System.out.println(maybeName.get());
}
```

More signatures you'll encounter in Spring Boot:

```java
JpaRepository<Employee, Long>  // repository for Employee entities with Long primary keys
ResponseEntity<List<Employee>> // an HTTP response containing a list of employees
Optional<Employee>             // used by repository findById — might not find anything
```

These are all just generics — the same `<T>` pattern you've learned in this chapter.

---

## Summary

| Concept | Key takeaway |
|---|---|
| Generics `<T>` | Type parameters that make code reusable and type-safe |
| Diamond `<>` | Compiler infers the type — `new ArrayList<>()` |
| `List<String>` vs raw `List` | Always use the typed version — safer, cleaner, no casts |
| `Box<T>`, `Pair<A, B>` | Generic classes — one definition, any type |
| `<T> T method(List<T>)` | Generic methods — type inferred from arguments |
| `<T extends Number>` | Bounded type — restrict what T can be |
| `?` wildcard | Unknown type — used when reading collections |
| `Repository<T, ID>` | Generic interfaces — the pattern behind Spring Data |
| `Optional<T>` | Maybe a value, maybe empty — avoids null |

**Next:** Maven & Spring Boot setup — you'll use these generic types everywhere.
