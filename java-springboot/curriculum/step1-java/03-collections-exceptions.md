# Chapter 3 — Collections & Exceptions

> **Goal:** Master the Java collections and exception patterns you will use every single day in Spring Boot. We skip advanced topics (JDBC, streams, complex lambdas) — those come later. Focus here is on building intuition fast.

---

## Part 1 — Collections

### Why Collections Matter in Spring Boot

Every Spring Boot application moves data around: lists of users returned from a database, maps of configuration values, optional results from a repository query. Java's collections framework is the backbone of all of that. If you understand `List`, `Map`, and `Optional`, you can read and write 90% of real Spring Boot code.

---

### 3.1 Typed Collections

Java collections are **explicitly typed** — you must declare what kind of objects a collection holds. This is part of Java's generics system (covered in Chapter 4). The angle brackets `<>` specify the element type:

```java
List<String>          // a list that holds only Strings
Map<String, Integer>  // a map with String keys and Integer values
```

The compiler enforces these types at compile time, which eliminates an entire class of runtime bugs. You cannot accidentally add an `Integer` to a `List<String>`.

---

### 3.2 `List<T>` and `ArrayList<T>`

`List<T>` is the **interface** (the contract). `ArrayList<T>` is the most common **implementation** (the actual class). You will almost always declare the variable as `List<T>` and create it with `new ArrayList<>()`. This is a Java best practice: program to the interface, not the implementation.

#### Creating a List

```java
import java.util.ArrayList;
import java.util.List;

// Declare as List<T>, instantiate as ArrayList<T>
List<String> names = new ArrayList<>();

// Or with initial values (creates a fixed-size list — cannot add/remove)
List<String> fixed = List.of("Alice", "Bob", "Charlie");

// For a mutable list with initial values
List<String> mutable = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));
```

#### Core Operations

```java
List<String> names = new ArrayList<>();

// add — append to the end
names.add("Alice");
names.add("Bob");
names.add("Charlie");
// names = ["Alice", "Bob", "Charlie"]

// add at index — insert at position
names.add(1, "Zara");
// names = ["Alice", "Zara", "Bob", "Charlie"]

// get — access by index
String first = names.get(0);  // "Alice"
String last  = names.get(names.size() - 1);  // "Charlie"

// size — number of elements
int count = names.size();  // 4

// contains — membership check
boolean hasAlice = names.contains("Alice");  // true
boolean hasDave  = names.contains("Dave");   // false

// remove by index
names.remove(1);  // removes "Zara"
// names = ["Alice", "Bob", "Charlie"]

// remove by value (first occurrence)
names.remove("Bob");
// names = ["Alice", "Charlie"]

// set — replace element at index
names.set(0, "Alicia");
// names = ["Alicia", "Charlie"]

// isEmpty and clear
boolean empty = names.isEmpty();  // false
names.clear();
System.out.println(names.isEmpty());  // true
```

#### Iterating Over a List

```java
List<String> departments = new ArrayList<>(List.of("Engineering", "HR", "Finance"));

// Option 1: enhanced for loop (most common — use this by default)
for (String dept : departments) {
    System.out.println(dept);
}

// Option 2: traditional index-based loop (use when you need the index)
for (int i = 0; i < departments.size(); i++) {
    System.out.println(i + ": " + departments.get(i));
}
```

#### Practical Example — Building a List from Logic

```java
public List<String> getSeniorEmployees(List<String> allEmployees, int minYears) {
    List<String> seniors = new ArrayList<>();
    // We'll add filtering logic here later
    // For now, imagine each name is paired with years (simplified)
    return seniors;
}
```

---

### 3.3 `Map<K,V>` and `HashMap<K,V>`

A `Map` stores key-value pairs. `HashMap` is the most common implementation — unordered, fast lookups.

#### Creating a Map

```java
import java.util.HashMap;
import java.util.Map;

// Declare as Map<K,V>, instantiate as HashMap<K,V>
Map<String, String> phoneBook = new HashMap<>();

// Or with initial values (Java 9+, immutable)
Map<String, Integer> scores = Map.of("Alice", 95, "Bob", 87);
```

#### Core Operations

```java
Map<String, String> phoneBook = new HashMap<>();

// put — add or update a key-value pair
phoneBook.put("Alice", "+44 7700 900001");
phoneBook.put("Bob",   "+44 7700 900002");
phoneBook.put("Alice", "+44 7700 999999");  // overwrites Alice's number

// get — retrieve by key (returns null if key doesn't exist — no exception!)
String aliceNumber = phoneBook.get("Alice");  // "+44 7700 999999"
String unknown     = phoneBook.get("Dave");   // null — no exception

// getOrDefault — safe retrieval with fallback
String safeNumber = phoneBook.getOrDefault("Dave", "Not found");  // "Not found"

// containsKey — check if a key exists
boolean hasAlice = phoneBook.containsKey("Alice");  // true
boolean hasDave  = phoneBook.containsKey("Dave");   // false

// remove — delete a key-value pair
phoneBook.remove("Bob");

// size
int count = phoneBook.size();  // 1

// isEmpty
boolean empty = phoneBook.isEmpty();  // false
```

> **Important:** `map.get("missing_key")` returns `null` silently in Java — it does not throw an exception. This is a common source of `NullPointerException` bugs. Always use `containsKey()` or `getOrDefault()` when a key might be absent.

#### Iterating Over a Map

```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.put("Bob",   87);
scores.put("Charlie", 92);

// Iterate over keys only
for (String name : scores.keySet()) {
    System.out.println(name);
}

// Iterate over values only
for (int score : scores.values()) {
    System.out.println(score);
}

// Iterate over key-value pairs — entrySet() is the most useful
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + " scored " + entry.getValue());
}
```

#### Practical Example — Counting Word Frequency

```java
public Map<String, Integer> countWords(List<String> words) {
    Map<String, Integer> frequency = new HashMap<>();

    for (String word : words) {
        // getOrDefault handles the "first time we see this word" case
        int current = frequency.getOrDefault(word, 0);
        frequency.put(word, current + 1);
    }

    return frequency;
}
```

---

### 3.4 When to Use Which

| Situation | Use |
|---|---|
| Ordered sequence of items | `List<T>` / `ArrayList<T>` |
| Fast lookup by a unique key | `Map<K,V>` / `HashMap<K,V>` |
| No duplicates needed | `Set<T>` / `HashSet<T>` (brief mention — not the focus today) |
| Result that might not exist | `Optional<T>` (covered next) |
| Need insertion-order in a Map | `LinkedHashMap<K,V>` |
| Need sorted keys in a Map | `TreeMap<K,V>` |

**Rule of thumb for Spring Boot:** You will mostly use `List<T>` for returning multiple records from the database and `Map<String, Object>` for ad-hoc JSON responses.

---

### 3.5 `Optional<T>` — Handling Absence Safely

`Optional<T>` is a container that either **holds a value** or **holds nothing**. Spring Data repositories return `Optional<T>` from `findById()` to force you to handle the "not found" case explicitly — no more silent `null` bugs.

#### Why Optional Exists

> **Note:** Spring Data JPA's `findById()` returns `Optional<T>` — not null — which we cover in the Optional section below. The plain-null example here uses a `HashMap` to show the danger of unchecked nulls.

```java
// Without Optional — dangerous (using a plain Map to illustrate null return)
Map<Long, Employee> cache = new HashMap<>();
cache.put(1L, new Employee("Alice"));

Employee emp = cache.get(99L);       // returns null silently — no exception!
System.out.println(emp.getName());   // NullPointerException if key not found!

// With Optional — safe
Optional<Employee> maybeEmp = Optional.ofNullable(cache.get(99L));
// You MUST handle the empty case
```

#### Creating an Optional (mostly done by the framework, but good to know)

```java
import java.util.Optional;

// Wrap a value
Optional<String> name = Optional.of("Alice");

// Wrap a possibly-null value
Optional<String> maybeName = Optional.ofNullable(someStringThatMightBeNull);

// Empty optional
Optional<String> nothing = Optional.empty();
```

#### Using an Optional

```java
Optional<Employee> result = employeeRepository.findById(42L);

// Check if a value is present
if (result.isPresent()) {
    Employee emp = result.get();
    System.out.println(emp.getName());
}

// isEmpty() — the opposite of isPresent()
if (result.isEmpty()) {
    System.out.println("Employee not found");
}

// orElse — provide a default value
Employee emp = result.orElse(new Employee("Unknown"));

// orElseThrow — throw an exception if empty (most common in Spring Boot)
Employee emp = result.orElseThrow(() -> new RuntimeException("Employee not found"));

```

#### The Spring Boot Pattern You Will Write Every Day

```java
// In a Spring Boot service class:
public Employee getEmployeeById(Long id) {
    return employeeRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
}
```

This is the pattern. Learn it. You will type it dozens of times.

---

### 3.6 Key Concepts — Java Collections

| Operation | Java |
|---|---|
| Create empty list | `List<String> names = new ArrayList<>()` |
| Append | `names.add("Alice")` |
| Access by index | `names.get(0)` |
| Length | `names.size()` |
| Check membership | `names.contains("Alice")` |
| Remove by value | `names.remove("Alice")` |
| Iterate | `for (String n : names) {` |
| Create empty map | `Map<String,String> d = new HashMap<>()` |
| Set value | `d.put("key", "val")` |
| Get value | `d.get("key")` |
| Get with default | `d.getOrDefault("key", "default")` |
| Check key | `d.containsKey("key")` |
| Iterate key-value | `for (Map.Entry<K,V> e : d.entrySet()) {` |
| Possibly absent value | `Optional<V> v = Optional.ofNullable(d.get("k"))` |

---

## Part 2 — Exceptions

### Why Exceptions Matter in Spring Boot

Spring Boot's entire error-handling mechanism is built around exceptions. When a request comes in and something goes wrong — entity not found, validation fails, unauthorised access — you throw an exception. Spring catches it, maps it to an HTTP status code, and returns a proper error response. Understanding how to create and throw exceptions is non-negotiable.

---

### 3.7 Checked vs Unchecked Exceptions

This is the most important distinction in Java exception handling, and it directly affects how you write Spring Boot code.

```
Throwable
├── Error               (JVM-level problems — never catch these)
└── Exception
    ├── IOException     (checked)
    ├── SQLException    (checked)
    └── RuntimeException (unchecked)
        ├── NullPointerException
        ├── IllegalArgumentException
        └── (your custom exceptions go here)
```

| | Checked Exception | Unchecked Exception |
|---|---|---|
| Extends | `Exception` | `RuntimeException` |
| Must declare with `throws`? | Yes | No |
| Must catch? | Yes (or declare) | No |
| Examples | `IOException`, `SQLException` | `NullPointerException`, `IllegalArgumentException` |
| Spring Boot preference | Rarely used | Almost always |

**Checked exceptions** force the caller to handle the error explicitly. The compiler will refuse to compile your code if you don't catch a checked exception or declare that your method throws it.

**Unchecked exceptions** (subclasses of `RuntimeException`) do not require declaration or mandatory catching. They propagate up the call stack automatically until something catches them — or the application terminates.

**Why Spring Boot prefers unchecked exceptions:** Spring has a global exception handler (`@ControllerAdvice`) that catches exceptions at the top level and converts them to HTTP responses. You don't want every intermediate method to be cluttered with `throws MyException` declarations. Just throw and let Spring handle it.

---

### 3.8 `try` / `catch` / `finally`

```java
try {
    // Code that might throw an exception
    String result = riskyOperation();
    System.out.println(result);

} catch (IllegalArgumentException e) {
    // Handle a specific exception type
    System.err.println("Bad argument: " + e.getMessage());

} catch (RuntimeException e) {
    // Catch a broader type — more specific catches must come FIRST
    System.err.println("Runtime error: " + e.getMessage());

} finally {
    // Always runs — whether an exception was thrown or not
    // Use for cleanup: closing connections, releasing resources
    System.out.println("Cleanup done.");
}
```

#### Practical Example — Parsing User Input

```java
public int parseAge(String input) {
    try {
        int age = Integer.parseInt(input);
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Age out of valid range: " + age);
        }
        return age;
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid age format: '" + input + "'", e);
    }
}
```

---

### 3.9 `throw` and `throws`

#### `throw` — actively throw an exception

```java
public void setAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative: " + age);
    }
    this.age = age;
}
```

#### `throws` — declare that a method can throw a checked exception

You will rarely need this in Spring Boot, but you must recognise it when reading code.

```java
// This method might throw IOException — caller must handle it
public String readFile(String path) throws IOException {
    return Files.readString(Path.of(path));
}

// Caller must either catch it or also declare throws
public void processFile() throws IOException {
    String content = readFile("/tmp/data.txt");
    System.out.println(content);
}
```

For unchecked exceptions, `throws` is optional and rarely written:

```java
// No throws needed for RuntimeException subclasses
public Employee findOrFail(Long id) {
    return employeeRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));
}
```

---

### 3.10 Creating Custom Exceptions

This is the pattern you will use in every Spring Boot project. Create custom exception classes that extend `RuntimeException`. Spring Boot's `@ControllerAdvice` will catch them and return appropriate HTTP responses.

#### The Basic Pattern

```java
// A custom exception for "resource not found" scenarios
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

#### A More Specific Exception

```java
public class EmployeeNotFoundException extends RuntimeException {

    private final Long employeeId;

    public EmployeeNotFoundException(Long employeeId) {
        super("Employee not found with id: " + employeeId);
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
```

#### Throwing Custom Exceptions

```java
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee getByEmail(String email) {
        Employee emp = employeeRepository.findByEmail(email);
        if (emp == null) {
            throw new NotFoundException("No employee with email: " + email);
        }
        return emp;
    }
}
```

#### How Spring Boot Handles It (Preview)

```java
// In a later chapter you'll write this:
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
// When NotFoundException is thrown anywhere, Spring automatically returns HTTP 404.
```

---

### 3.11 Common Exceptions to Know

| Exception | When it happens | How to prevent it |
|---|---|---|
| `NullPointerException` | Calling a method on a `null` reference | Use `Optional`, null checks, or `@NonNull` annotations |
| `IllegalArgumentException` | A method receives an invalid argument | Validate inputs at method entry |
| `IllegalStateException` | Object is in wrong state for the operation | Check object state before calling |
| `IndexOutOfBoundsException` | Accessing `list.get(i)` where `i >= list.size()` | Check `list.size()` first |
| `ClassCastException` | Casting an object to an incompatible type | Use `instanceof` before casting |
| `NumberFormatException` | `Integer.parseInt("abc")` | Wrap in try/catch or validate input format |
| `UnsupportedOperationException` | Calling `add()` on a `List.of(...)` (immutable list) | Use `new ArrayList<>(List.of(...))` |

#### The Most Important One: NullPointerException

> **Note:** Spring Data JPA's `findById()` returns `Optional<T>` — not null — which we cover in the Optional section below. The examples below use a plain `Map` to illustrate what raw null returns look like and how to guard against them.

```java
Map<Long, Employee> store = new HashMap<>();
store.put(1L, new Employee("Alice"));

// Dangerous — map.get() returns null when the key is missing
Employee emp = store.get(99L);   // returns null silently
String name = emp.getName();     // NullPointerException!

// Safe — always check before using a potentially null value
Employee emp2 = store.get(99L);
if (emp2 != null) {
    String name2 = emp2.getName();
}

// Best — wrap the map lookup in Optional to make absence explicit
Optional<Employee> maybeEmp = Optional.ofNullable(store.get(99L));
if (maybeEmp.isPresent()) {
    String name3 = maybeEmp.get().getName();
}
```

---

### 3.12 Why Spring Boot Mostly Uses Unchecked Exceptions

Here is the practical reason with a code example:

```java
// If NotFoundException were a CHECKED exception, every service and controller
// would have to declare it — this gets messy fast:
public Employee getById(Long id) throws NotFoundException { ... }           // service
public ResponseEntity<Employee> getEmployee(Long id) throws NotFoundException { ... } // controller
public void processEmployee(Long id) throws NotFoundException { ... }       // another service

// With an UNCHECKED exception, it just propagates to Spring's global handler:
public Employee getById(Long id) { ... }                    // clean
public ResponseEntity<Employee> getEmployee(Long id) { ... } // clean
public void processEmployee(Long id) { ... }                 // clean
// Spring catches the exception at the top level — no clutter anywhere
```

The Spring Framework team made this choice deliberately. All Spring's own exceptions (`DataAccessException`, `HttpClientErrorException`, etc.) extend `RuntimeException`.

---

### Summary

| Concept | Key takeaway |
|---|---|
| `List<T>` | Ordered, indexed, allows duplicates — use for sequences |
| `Map<K,V>` | Key-value pairs, fast lookup — use when you need to find by key |
| `Optional<T>` | Wrap a value that might not exist — use `.orElseThrow()` in Spring |
| Checked exceptions | Compiler-enforced, must catch or declare — rare in Spring Boot |
| Unchecked exceptions | Extend `RuntimeException`, propagate freely — the Spring default |
| Custom exceptions | Extend `RuntimeException`, throw them, let Spring handle the HTTP mapping |

**Next:** Chapter 4 — Annotations & Generics. This is where Java starts to look like Spring Boot.
