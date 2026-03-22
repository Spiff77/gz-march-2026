# Lab 3 — Collections & Exceptions

> **Corresponding chapter:** Chapter 3 — Collections & Exceptions
>
> **Time estimate:** 60–80 minutes
>
> **Prerequisites:** Chapters 1–3. You should be comfortable with classes, methods, and basic Java syntax.

---

## Overview

This lab puts `List<T>`, `Map<K,V>`, `Optional<T>`, and custom exceptions into practice through five focused exercises. Each exercise mirrors a pattern you will use repeatedly in Spring Boot — repository lookups, grouping data, throwing and catching domain exceptions, and filtering collections.

**You do not need Spring Boot to complete this lab.** All exercises run as plain Java — `main` methods or simple test calls.

---

## Exercise 1 — Phone Book with `HashMap`

### Goal

Build a simple phone book application using `HashMap<String, String>`. Practice the full lifecycle of map operations: add, lookup, update, and delete.

### What to implement

Create a class called `PhoneBook` with the following methods:

```java
public class PhoneBook {

    // Internal storage — a map from contact name to phone number
    private Map<String, String> contacts = new HashMap<>();

    // Add a new contact, or update if already exists
    public void addContact(String name, String number) { ... }

    // Look up a contact's number — return the number, or "Contact not found." if missing
    public String lookup(String name) { ... }

    // Delete a contact — if not found, print a message instead of crashing
    public void deleteContact(String name) { ... }

    // Print all contacts in the format: "Name: number"
    public void printAll() { ... }

    // Return the number of contacts
    public int size() { ... }
}
```

### Starter code

```java
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    private Map<String, String> contacts = new HashMap<>();

    public void addContact(String name, String number) {
        // TODO: add or update the contact
    }

    public String lookup(String name) {
        // TODO: return the number, or "Contact not found." if not present
        // Hint: use containsKey() or getOrDefault()
        return null;
    }

    public void deleteContact(String name) {
        // TODO: remove if present, print "No contact named X." if not found
    }

    public void printAll() {
        // TODO: iterate and print each contact
        // Hint: use entrySet() for key + value together
    }

    public int size() {
        // TODO: return the number of contacts
        return 0;
    }

    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        book.addContact("Alice",   "+44 7700 900001");
        book.addContact("Bob",     "+44 7700 900002");
        book.addContact("Charlie", "+44 7700 900003");

        System.out.println(book.lookup("Alice"));    // +44 7700 900001
        System.out.println(book.lookup("Dave"));     // Contact not found.

        book.addContact("Alice", "+44 7700 999999"); // update Alice
        System.out.println(book.lookup("Alice"));    // +44 7700 999999

        book.deleteContact("Bob");
        book.deleteContact("Eve");  // should print a message, not crash

        System.out.println("Total contacts: " + book.size());  // 2
        book.printAll();
    }
}
```

### Expected output

```
+44 7700 900001
Contact not found.
+44 7700 999999
No contact named Eve.
Total contacts: 2
Alice: +44 7700 999999
Charlie: +44 7700 900003
```

### Key methods to use

- `HashMap.put(key, value)` — add or overwrite
- `HashMap.get(key)` — returns `null` if missing
- `HashMap.getOrDefault(key, defaultValue)` — safe retrieval
- `HashMap.containsKey(key)` — check before acting
- `HashMap.remove(key)` — delete
- `HashMap.size()` — count
- `HashMap.entrySet()` — iterate key-value pairs

---

## Exercise 2 — Group Words by First Letter

### Goal

Write a method that takes a `List<String>` of words and groups them by their first letter, returning a `Map<Character, List<String>>`. This pattern — grouping items into buckets — comes up constantly in real applications (e.g., grouping employees by department, orders by status).

### What to implement

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordGrouper {

    /**
     * Groups a list of words by their first letter.
     *
     * Example input:  ["apple", "avocado", "banana", "blueberry", "cherry"]
     * Example output: { 'a': ["apple", "avocado"], 'b': ["banana", "blueberry"], 'c': ["cherry"] }
     *
     * Words with the same first letter go into the same list.
     * Ignore empty strings.
     */
    public static Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        // TODO: implement

        // Hint: for each word, extract the first character with word.charAt(0)
        // Check if the map already has a list for that character
        // If not, create a new ArrayList and put it in the map
        // Then add the word to that list

        return null;
    }

    public static void main(String[] args) {
        List<String> words = List.of(
            "apple", "avocado", "banana", "blueberry",
            "cherry", "apricot", "blackberry", "clementine"
        );

        Map<Character, List<String>> grouped = groupByFirstLetter(words);

        // Print each group
        for (Map.Entry<Character, List<String>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
```

### Expected output (order may vary — HashMap is unordered)

```
a -> [apple, avocado, apricot]
b -> [banana, blueberry, blackberry]
c -> [cherry, clementine]
```

### Challenge extension

Modify the method to also handle case-insensitively — `"Apple"` and `"apple"` should go in the same `'a'` bucket. Use `Character.toLowerCase(word.charAt(0))`.

### Java implementation note

In Java, you can either use a manual `containsKey` check (explicit and easy to follow) or `computeIfAbsent` (more concise). Both produce the same result — the manual check is recommended here because it makes the logic explicit:

```java
// Manual check — recommended for learning
if (!map.containsKey(key)) {
    map.put(key, new ArrayList<>());
}
map.get(key).add(word);

// computeIfAbsent — equivalent, more concise
map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
```

---

## Exercise 3 — Custom `NotFoundException`

### Goal

Create a custom `NotFoundException` that extends `RuntimeException`. Use it in a contact lookup system that throws the exception when a contact is not found, rather than returning a sentinel value like `"Contact not found."`.

This is the exact pattern used in Spring Boot services.

### What to implement

**Step 1:** Create the custom exception class.

```java
/**
 * Thrown when a requested resource cannot be found.
 * Extends RuntimeException so callers are not forced to catch it.
 * Spring Boot maps this to HTTP 404 when paired with @ControllerAdvice.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        // TODO: call the parent constructor with the message
    }

    public NotFoundException(String message, Throwable cause) {
        // TODO: call the parent constructor with message and cause
    }
}
```

**Step 2:** Create a `ContactService` that uses it.

```java
import java.util.HashMap;
import java.util.Map;

public class ContactService {

    private final Map<String, String> contacts = new HashMap<>();

    public ContactService() {
        contacts.put("Alice",   "+44 7700 900001");
        contacts.put("Bob",     "+44 7700 900002");
        contacts.put("Charlie", "+44 7700 900003");
    }

    /**
     * Returns the phone number for the given name.
     * Throws NotFoundException if the contact does not exist.
     */
    public String getNumber(String name) {
        // TODO: look up the contact
        // If found, return the number
        // If not found, throw new NotFoundException("Contact not found: " + name)
        return null;
    }

    /**
     * Deletes a contact. Throws NotFoundException if the contact does not exist.
     */
    public void deleteContact(String name) {
        // TODO: check if contact exists, throw NotFoundException if not, then remove
    }

    public static void main(String[] args) {

        ContactService service = new ContactService();

        // Successful lookups
        System.out.println(service.getNumber("Alice"));   // +44 7700 900001
        System.out.println(service.getNumber("Bob"));     // +44 7700 900002

        // Demonstrate exception handling
        try {
            service.getNumber("Dave");  // not found
        } catch (NotFoundException e) {
            System.out.println("Caught: " + e.getMessage());  // Caught: Contact not found: Dave
        }

        // Show that NotFoundException propagates naturally (without try/catch, it terminates the app)
        service.deleteContact("Alice");  // succeeds

        try {
            service.deleteContact("Alice");  // already deleted — throws NotFoundException
        } catch (NotFoundException e) {
            System.out.println("Caught on delete: " + e.getMessage());
        }
    }
}
```

### Expected output

```
+44 7700 900001
+44 7700 900002
Caught: Contact not found: Dave
Caught on delete: Contact not found: Alice
```

### Key concepts

- `RuntimeException` subclasses do not require `throws` declarations
- `super(message)` in the constructor passes the message to `RuntimeException`, which stores it
- `e.getMessage()` retrieves the message you passed to the constructor
- In Spring Boot, `@ExceptionHandler(NotFoundException.class)` would catch this and return HTTP 404

---

## Exercise 4 — `Optional<T>` and `orElseThrow`

### Goal

Practice the `Optional<T>` pattern that Spring Data uses for repository lookups. Write a `findEmployee` method that returns `Optional<Employee>`, and chain `orElseThrow` to extract the value or throw `NotFoundException`.

### Setup — Employee class

```java
public class Employee {

    private Long id;
    private String name;
    private String department;
    private double salary;

    public Employee(Long id, String name, String department, double salary) {
        this.id         = id;
        this.name       = name;
        this.department = department;
        this.salary     = salary;
    }

    public Long   getId()         { return id; }
    public String getName()       { return name; }
    public String getDepartment() { return department; }
    public double getSalary()     { return salary; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', department='" + department + "'}";
    }
}
```

### What to implement

```java
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

    // ---- Calling code — implement getById and getByName in EmployeeService ----

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
```

```java
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
```

### Expected output

```
Found: Employee{id=1, name='Alice Chen', department='Engineering'}
Found: Employee{id=3, name='Carol White', department='Engineering'}
isPresent: false
isEmpty:   true
orElse:    Employee{id=0, name='Unknown', department='N/A'}
Caught: Employee not found with id: 99
```

### Key `Optional` methods to understand

| Method | Behaviour |
|---|---|
| `Optional.of(value)` | Wrap a non-null value — throws if null |
| `Optional.ofNullable(value)` | Wrap a possibly-null value |
| `Optional.empty()` | An empty optional |
| `.isPresent()` | `true` if a value is present |
| `.isEmpty()` | `true` if no value is present |
| `.get()` | Get the value — throws if empty (avoid using directly) |
| `.orElse(default)` | Return value, or default if empty |
| `.orElseThrow(supplier)` | Return value, or throw exception if empty |

---

## Exercise 5 — Mini Challenge: Employee Roster

### Goal

Build a small in-memory employee roster that supports adding employees, filtering by department, and finding the highest-paid employee in a department. No streams, no lambdas — manual iteration only. This exercises `List<T>` and the `Employee` class from Exercise 4.

### What to implement

```java
import java.util.ArrayList;
import java.util.List;

public class EmployeeRoster {

    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        // TODO: add to the list
    }

    /**
     * Return a list of all employees in the given department.
     * Return an empty list if no employees match.
     *
     * Do NOT use streams. Use a for loop and an if statement.
     */
    public List<Employee> getByDepartment(String department) {
        List<Employee> result = new ArrayList<>();
        // TODO: loop through employees, add those whose department matches
        //       Use equalsIgnoreCase for case-insensitive matching
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

        // TODO: find the employee with the highest salary in inDept
        // Hint: start with inDept.get(0) as the current best,
        //       then loop through the rest comparing .getSalary()
        return null;
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
        // CHALLENGE: build a Map<String, List<Employee>> grouping employees by department
        // Then iterate and print each group
        // This combines Exercise 2's grouping logic with the Employee class
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
            System.out.println("  - " + e.getName() + " (£" + e.getSalary() + ")");
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

        // CHALLENGE: print the full roster
        System.out.println();
        roster.printRoster();
    }
}
```

### Expected output

```
Total employees: 7

Engineering team (3):
  - Alice Chen (£85000.0)
  - Carol White (£91000.0)
  - Emma Wilson (£78000.0)

Highest paid in Engineering: Carol White
Highest paid in Finance: Grace Okonkwo

Caught: No employees in department: Legal

Engineering (3):
  - Alice Chen
  - Carol White
  - Emma Wilson
Finance (2):
  - David Kumar
  - Grace Okonkwo
HR (2):
  - Bob Smith
  - Frank Zhang
```

### Skills practised

- Adding to and iterating over `List<T>`
- Filtering a list with a manual loop — the pattern before streams
- Tracking a "best so far" value across a loop
- Combining `List<T>` and `Map<K,V>` to group data
- Throwing `NotFoundException` for invalid operations

---

## Completion Checklist

- [ ] Exercise 1: `PhoneBook` with all five methods working correctly
- [ ] Exercise 2: `groupByFirstLetter` groups correctly, handles the extension
- [ ] Exercise 3: `NotFoundException` defined, thrown, and caught appropriately
- [ ] Exercise 4: `findById` and `findByName` return correct `Optional` values; `orElseThrow` works
- [ ] Exercise 5: `getByDepartment` filters correctly; `getHighestPaid` finds correct result; `printRoster` prints grouped output

## Stretch Goals

- In Exercise 1, add a method `searchByNumber(String number)` that finds all contacts with a given phone number — this requires iterating values and is `O(n)` vs the `O(1)` key lookup.
- In Exercise 5, add a method `removeEmployee(Long id)` that throws `NotFoundException` if the employee does not exist.
- In Exercise 5, add a method `getSalaryBudget(String department)` that returns the total salary for a department.

---

## Solutions

<details>
<summary>Solution — Exercise 1: Phone Book with HashMap</summary>

```java
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    private Map<String, String> contacts = new HashMap<>();

    public void addContact(String name, String number) {
        contacts.put(name, number);
    }

    public String lookup(String name) {
        return contacts.getOrDefault(name, "Contact not found.");
    }

    public void deleteContact(String name) {
        if (!contacts.containsKey(name)) {
            System.out.println("No contact named " + name + ".");
        } else {
            contacts.remove(name);
        }
    }

    public void printAll() {
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public int size() {
        return contacts.size();
    }

    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        book.addContact("Alice",   "+44 7700 900001");
        book.addContact("Bob",     "+44 7700 900002");
        book.addContact("Charlie", "+44 7700 900003");

        System.out.println(book.lookup("Alice"));    // +44 7700 900001
        System.out.println(book.lookup("Dave"));     // Contact not found.

        book.addContact("Alice", "+44 7700 999999"); // update Alice
        System.out.println(book.lookup("Alice"));    // +44 7700 999999

        book.deleteContact("Bob");
        book.deleteContact("Eve");  // prints message, doesn't crash

        System.out.println("Total contacts: " + book.size());  // 2
        book.printAll();
    }
}
```

</details>

<details>
<summary>Solution — Exercise 2: Group Words by First Letter</summary>

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordGrouper {

    public static Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        Map<Character, List<String>> result = new HashMap<>();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            char key = Character.toLowerCase(word.charAt(0));
            if (!result.containsKey(key)) {
                result.put(key, new ArrayList<>());
            }
            result.get(key).add(word);
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> words = List.of(
            "apple", "avocado", "banana", "blueberry",
            "cherry", "apricot", "blackberry", "clementine"
        );

        Map<Character, List<String>> grouped = groupByFirstLetter(words);

        for (Map.Entry<Character, List<String>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
```

</details>

<details>
<summary>Solution — Exercise 3: Custom NotFoundException</summary>

```java
// NotFoundException.java
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

```java
// ContactService.java
import java.util.HashMap;
import java.util.Map;

public class ContactService {

    private final Map<String, String> contacts = new HashMap<>();

    public ContactService() {
        contacts.put("Alice",   "+44 7700 900001");
        contacts.put("Bob",     "+44 7700 900002");
        contacts.put("Charlie", "+44 7700 900003");
    }

    public String getNumber(String name) {
        if (!contacts.containsKey(name)) {
            throw new NotFoundException("Contact not found: " + name);
        }
        return contacts.get(name);
    }

    public void deleteContact(String name) {
        if (!contacts.containsKey(name)) {
            throw new NotFoundException("Contact not found: " + name);
        }
        contacts.remove(name);
    }

    public static void main(String[] args) {
        ContactService service = new ContactService();

        System.out.println(service.getNumber("Alice"));
        System.out.println(service.getNumber("Bob"));

        try {
            service.getNumber("Dave");
        } catch (NotFoundException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        service.deleteContact("Alice");

        try {
            service.deleteContact("Alice");
        } catch (NotFoundException e) {
            System.out.println("Caught on delete: " + e.getMessage());
        }
    }
}
```

</details>

<details>
<summary>Solution — Exercise 4: Optional&lt;T&gt; and orElseThrow</summary>

```java
// EmployeeRepository.java
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EmployeeRepository {

    private final Map<Long, Employee> database = new HashMap<>();

    public EmployeeRepository() {
        database.put(1L, new Employee(1L, "Alice Chen",   "Engineering", 85000));
        database.put(2L, new Employee(2L, "Bob Smith",    "HR",          62000));
        database.put(3L, new Employee(3L, "Carol White",  "Engineering", 91000));
        database.put(4L, new Employee(4L, "David Kumar",  "Finance",     74000));
    }

    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public Optional<Employee> findByName(String name) {
        for (Employee emp : database.values()) {
            if (emp.getName().equalsIgnoreCase(name)) {
                return Optional.of(emp);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        EmployeeRepository repo    = new EmployeeRepository();
        EmployeeService     service = new EmployeeService(repo);

        Employee alice = service.getById(1L);
        System.out.println("Found: " + alice);

        Employee carol = service.getByName("carol white");
        System.out.println("Found: " + carol);

        Optional<Employee> maybeEmp = repo.findById(99L);
        System.out.println("isPresent: " + maybeEmp.isPresent());
        System.out.println("isEmpty:   " + maybeEmp.isEmpty());

        Employee fallback = maybeEmp.orElse(new Employee(0L, "Unknown", "N/A", 0));
        System.out.println("orElse:    " + fallback);

        try {
            service.getById(99L);
        } catch (NotFoundException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
```

```java
// EmployeeService.java
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
    }

    public Employee getByName(String name) {
        return repository.findByName(name)
            .orElseThrow(() -> new NotFoundException("Employee not found with name: " + name));
    }
}
```

</details>

<details>
<summary>Solution — Exercise 5: Mini Challenge: Employee Roster</summary>

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRoster {

    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getByDepartment(String department) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getDepartment().equalsIgnoreCase(department)) {
                result.add(e);
            }
        }
        return result;
    }

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

    public int totalCount() {
        return employees.size();
    }

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

        System.out.println("Total employees: " + roster.totalCount());

        List<Employee> engineers = roster.getByDepartment("Engineering");
        System.out.println("\nEngineering team (" + engineers.size() + "):");
        for (Employee e : engineers) {
            System.out.println("  - " + e.getName() + " (£" + e.getSalary() + ")");
        }

        Employee topEng = roster.getHighestPaid("Engineering");
        System.out.println("\nHighest paid in Engineering: " + topEng.getName());

        Employee topFin = roster.getHighestPaid("Finance");
        System.out.println("Highest paid in Finance: " + topFin.getName());

        try {
            roster.getHighestPaid("Legal");
        } catch (NotFoundException e) {
            System.out.println("\nCaught: " + e.getMessage());
        }

        System.out.println();
        roster.printRoster();
    }
}
```

</details>
