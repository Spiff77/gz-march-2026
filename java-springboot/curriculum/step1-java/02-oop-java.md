# Chapter 2 — Object-Oriented Programming in Java

---

## Learning Objectives

By the end of this chapter, you will be able to:

- Define classes with fields, constructors, and methods
- Apply access modifiers correctly (`public`, `private`, `protected`)
- Write getters and setters following Java conventions
- Use `this` and `super` appropriately
- Build inheritance hierarchies with `extends` and `@Override`
- Define and implement interfaces with `implements`
- Explain polymorphism, use `instanceof` to detect types at runtime
- Understand why interfaces are foundational to Spring Boot

> **Instructor note:** This chapter is content-dense. Aim to cover Sections 1–7 (classes through static members) before lunch, then Sections 8–12 (inheritance through polymorphism) in the afternoon session. Do not rush polymorphism — it is foundational for understanding Spring's dependency injection.

---

## 1. Classes and Objects

A **class** is a blueprint. An **object** is an instance of that blueprint. Every Java program is built from classes.

```java
public class BankAccount {
    private String owner;   // private — only this class can access it directly
    private double balance; // private — only this class can access it directly

    BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    void deposit(double amount) {
        this.balance += amount;
    }
}

// Creating an object (instance)
BankAccount account = new BankAccount("Alice", 1000.0);
account.deposit(500.0);
```

Key points:
- Use `new` to create an object from a class
- Every variable and parameter must declare its type
- The constructor has the same name as the class and no return type
- `this` refers to the current object — used to distinguish fields from parameters with the same name

---

## 2. Fields and Access Modifiers

**Fields** are variables that belong to a class (instance state). Every field and method has an **access modifier** that controls which other code can see it.

| Modifier          | Accessible from                                        |
|-------------------|--------------------------------------------------------|
| `public`          | Everywhere                                             |
| `private`         | Only within the same class                             |
| `protected`       | Same class + subclasses + same package                 |
| (package-private) | Same package only — the default when no keyword is written |

```java
public class Employee {
    // private — only accessible within Employee class
    private int id;
    private String name;
    private double salary;

    // package-private (no modifier) — accessible within same package
    String department;

    // protected — accessible in Employee and its subclasses
    protected String role;

    // public — accessible from anywhere
    public static final String COMPANY = "Training";
}
```

> **Rule of thumb:** make fields `private` by default. Expose them through methods you explicitly choose to make public. This is **encapsulation** — a core OOP principle.

---

## 3. Constructors

A constructor initializes an object when it is created with `new`. It has the same name as the class and no return type (not even `void`).

### 3.1 Default Constructor

If you define no constructor, Java provides a default no-argument constructor automatically. Once you define any constructor yourself, the default is no longer provided.

```java
public class Currency {
    private String code;
    private String name;

    // No constructor defined → Java provides one implicitly
    // Currency c = new Currency(); works, but fields are null/0
}
```

### 3.2 Parameterized Constructor

```java
public class Currency {
    private String code;
    private String name;
    private boolean isActive;

    // Parameterized constructor
    public Currency(String code, String name, boolean isActive) {
        this.code = code;
        this.name = name;
        this.isActive = isActive;
    }
}

// Usage
Currency gbp = new Currency("GBP", "British Pound", true);
Currency xxx = new Currency("XXX", "Unknown", false);
```

### 3.3 Constructor Overloading

You can have multiple constructors with different parameters — very common in practice:

```java
public class Transaction {
    private String id;
    private double amount;
    private String currency;
    private String description;

    // Minimal constructor
    public Transaction(String id, double amount) {
        this.id = id;
        this.amount = amount;
        this.currency = "GBP";      // sensible default
        this.description = "";
    }

    // Full constructor
    public Transaction(String id, double amount, String currency, String description) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    // Constructor that calls another constructor — use this()
    public Transaction(String id, double amount, String currency) {
        this(id, amount, currency, ""); // delegates to full constructor
    }
}
```

---

## 4. Getters and Setters

Because fields are `private`, external code cannot read or write them directly. Getters and setters are public methods that provide controlled access.

Java uses getters and setters rather than exposing fields directly because changing a public field to a method later would break all callers — the calling syntax is different. Using methods from the start gives you the flexibility to add validation, logging, or computed values without breaking anything.

```java
public class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters — naming convention: get + CapitalizedFieldName
    public int getId()         { return id; }
    public String getName()    { return name; }
    public double getSalary()  { return salary; }

    // Setters — naming convention: set + CapitalizedFieldName
    // Note: not every field needs a setter — immutable fields have only getters
    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        // Setters can enforce validation — this is the key advantage
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }

    // Boolean fields use "is" prefix by convention
    private boolean active;
    public boolean isActive()              { return active; }
    public void setActive(boolean active)  { this.active = active; }
}

// Usage
Employee emp = new Employee(101, "Priya Sharma", 75000.0);
System.out.println(emp.getName());   // "Priya Sharma"
emp.setSalary(80000.0);              // ok
emp.setSalary(-100);                 // throws IllegalArgumentException
```

> **In Spring Boot:** Spring uses the getter/setter naming convention to automatically map JSON fields to Java objects. A field named `firstName` must have `getFirstName()` / `setFirstName()` for Spring's JSON serialization to work. You'll see this when we cover REST controllers.

---

## 5. The `this` Keyword

`this` refers to the current object — the instance on which a method or constructor is being called. It has three main uses:

```java
public class Account {
    private String owner;
    private double balance;

    // Use 1: distinguish field from parameter with the same name
    public Account(String owner, double balance) {
        this.owner = owner;     // this.owner = field; owner = parameter
        this.balance = balance;
    }

    // Use 2: pass the current object to another method
    public void register(AccountRegistry registry) {
        registry.add(this);   // "add me to the registry"
    }

    // Use 3: call another constructor from within a constructor
    public Account(String owner) {
        this(owner, 0.0); // calls the two-parameter constructor above
    }
}
```

---

## 6. Inheritance with `extends`

Inheritance lets one class (the **subclass**) inherit fields and methods from another (the **superclass**). Java uses the `extends` keyword.

```java
public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public String speak() {
        return "...";
    }
}

public class Dog extends Animal {
    private String breed;

    public Dog(String name, String breed) {
        super(name);   // call Animal's constructor — MUST be first line
        this.breed = breed;
    }

    @Override
    public String speak() {
        return "Woof!";
    }

    public String getBreed() { return breed; }
}

// Usage
Dog dog = new Dog("Rex", "Labrador");
System.out.println(dog.getName());   // "Rex" — inherited from Animal
System.out.println(dog.speak());     // "Woof!" — overridden in Dog
System.out.println(dog.getBreed());  // "Labrador" — Dog-specific
```

> **Java has single inheritance:** a class can only `extend` one class. To share behavior from multiple sources, use interfaces (covered next).

---

## 7. @Override

`@Override` is an **annotation** — a metadata marker you place before a method to signal that it is intentionally replacing a method from the parent class.

```java
public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return "Meow!";
    }
}
```

Why use `@Override`? The compiler checks that you are actually overriding something. If you make a typo (`spek()` instead of `speak()`), without `@Override` Java silently creates a new method. With `@Override`, it gives you a compile error immediately.

```java
@Override
public String spek() { // COMPILE ERROR: method does not override anything
    return "Woof!";
}
```

**Always use `@Override`.** It is both documentation and a safety net.

---

## 8. The `super` Keyword

`super` refers to the parent class. It has two uses:

```java
public class Manager extends Employee {

    private int teamSize;

    public Manager(int id, String name, double salary, int teamSize) {
        super(id, name, salary); // call Employee's constructor
        this.teamSize = teamSize;
    }

    @Override
    public String toString() {
        // Call the parent's toString() and extend it
        return super.toString() + ", manages " + teamSize + " people";
    }
}
```

---

## 9. Interfaces

An interface is a **contract**. It defines a set of methods that any implementing class must provide, without specifying how. Interfaces are the most important abstraction mechanism in Java — and in Spring Boot, they are everywhere.

### 9.1 Defining and Implementing an Interface

```java
// Define the contract
public interface Printable {
    void print();  // implicitly public and abstract
    String getTitle(); // every implementing class must provide this
}

// A class can implement one or more interfaces
public class Invoice implements Printable {
    private String title;
    private double amount;

    public Invoice(String title, double amount) {
        this.title = title;
        this.amount = amount;
    }

    @Override
    public void print() {
        System.out.printf("Invoice: %s — £%.2f%n", title, amount);
    }

    @Override
    public String getTitle() {
        return title;
    }
}
```

### 9.2 Implementing Multiple Interfaces

Unlike inheritance, a class can implement **multiple interfaces**:

```java
public interface Serializable {
    String toJson();
}

public interface Auditable {
    String getAuditLog();
}

// Implements both contracts
public class Payment implements Printable, Serializable, Auditable {

    private String id;
    private double amount;

    public Payment(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public void print() {
        System.out.println("Payment " + id + ": £" + amount);
    }

    @Override
    public String getTitle() { return "Payment " + id; }

    @Override
    public String toJson() {
        return String.format("{\"id\":\"%s\",\"amount\":%.2f}", id, amount);
    }

    @Override
    public String getAuditLog() {
        return "Payment " + id + " processed at " + java.time.LocalDateTime.now();
    }
}
```

### 9.3 Interfaces with Default Methods (Java 8+)

Interfaces can provide default implementations since Java 8:

```java
public interface Notifiable {

    void sendEmail(String recipient, String message);  // must be implemented

    // Default method — implementing class can use as-is or override
    default void sendUrgentEmail(String recipient, String message) {
        sendEmail(recipient, "[URGENT] " + message);
    }
}
```

### 9.4 Why Interfaces Are Fundamental to Spring Boot

> **This is the most important concept in this chapter for your Spring Boot work.**

Spring Boot's dependency injection relies entirely on interfaces. Here is the pattern you will see constantly:

```java
// 1. Define a contract (interface)
public interface AccountRepository {
    Account findById(int id);
    void save(Account account);
    List<Account> findAll();
}

// 2. Provide one or more implementations
public class DatabaseAccountRepository implements AccountRepository {
    @Override
    public Account findById(int id) {
        // actual database query
    }
    // ...
}

public class InMemoryAccountRepository implements AccountRepository {
    @Override
    public Account findById(int id) {
        // in-memory lookup — great for testing
    }
    // ...
}

// 3. Your service depends on the interface, not a specific implementation
public class AccountService {
    private AccountRepository repository; // type is the interface

    // Spring will inject the correct implementation automatically
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account getAccount(int id) {
        return repository.findById(id); // doesn't know or care which impl
    }
}
```

You can swap `DatabaseAccountRepository` for `InMemoryAccountRepository` (e.g., for testing) without changing `AccountService` at all. This is **Dependency Inversion** — one of the core principles behind maintainable software.

---

## 10. Polymorphism

Polymorphism means "many forms." A reference of a parent type can point to an object of any subtype, and the correct method is called based on the actual object type at runtime.

```java
Animal[] animals = {
    new Dog("Rex", "Labrador"),
    new Cat("Whiskers"),
    new Dog("Buddy", "Beagle")
};

for (Animal animal : animals) {
    // Java calls the correct speak() at runtime based on actual type
    System.out.println(animal.getName() + " says: " + animal.speak());
}
// Output:
// Rex says: Woof!
// Whiskers says: Meow!
// Buddy says: Woof!
```

The same works with interfaces:

```java
List<Printable> documents = new ArrayList<>();
documents.add(new Invoice("INV-001", 1500.0));
documents.add(new Payment("PAY-042", 750.0));

for (Printable doc : documents) {
    doc.print(); // correct implementation called for each object
}
```

---

## 11. instanceof

`instanceof` checks whether an object is an instance of a given class or interface:

```java
Animal animal = new Dog("Rex", "Labrador");

System.out.println(animal instanceof Animal);  // true
System.out.println(animal instanceof Dog);     // true
System.out.println(animal instanceof Cat);     // false

// Check and cast — use instanceof first, then cast explicitly
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;  // explicit cast to access Dog-specific methods
    System.out.println("Breed: " + dog.getBreed());
}
```

---

## 12. Full Example — Employee Hierarchy

Let's bring together everything in a realistic example:

```java
import java.util.ArrayList;
import java.util.List; // List<T> and ArrayList<T> are covered in depth in Chapter 3

// Base class — regular class with concrete methods
public class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId()        { return id; }
    public String getName()   { return name; }
    public double getSalary() { return salary; }

    public double totalCompensation() {
        return salary; // base employees receive salary only
    }

    @Override
    public String toString() {
        return String.format("[%d] %s — Salary: £%.2f | Total: £%.2f",
                id, name, salary, totalCompensation());
    }
}

// Subclass: Manager — extends Employee and adds bonus logic
public class Manager extends Employee {

    private List<Employee> team = new ArrayList<>();
    private double bonusRate;

    public Manager(int id, String name, double salary, double bonusRate) {
        super(id, name, salary);
        this.bonusRate = bonusRate;
    }

    public void addTeamMember(Employee employee) {
        team.add(employee);
    }

    public double calculateBonus() {
        return getSalary() * bonusRate;
    }

    @Override
    public double totalCompensation() {
        return getSalary() + calculateBonus();
    }

    public void printTeamReport() {
        System.out.println("=== Team Report: " + getName() + " ===");
        for (Employee member : team) {
            System.out.println("  " + member);
        }
        System.out.printf("  Team size: %d%n", team.size());
    }
}

// Using it all together
public class Main {
    public static void main(String[] args) {
        Manager sarah = new Manager(1, "Sarah Chen", 120000, 0.20);
        sarah.addTeamMember(new Employee(10, "James Liu", 65000));
        sarah.addTeamMember(new Employee(11, "Aisha Patel", 68000));

        System.out.println(sarah);
        sarah.printTeamReport();

        // Polymorphism in action — Manager is-an Employee
        Employee[] allStaff = {sarah, new Employee(20, "Fatima Hassan", 72000)};
        for (Employee emp : allStaff) {
            System.out.println(emp.getName() + " total: £" + emp.totalCompensation());

            // instanceof to detect managers and access manager-specific methods
            if (emp instanceof Manager) {
                Manager mgr = (Manager) emp;
                System.out.println("  Bonus: £" + mgr.calculateBonus());
            }
        }
    }
}
```

Expected output:
```
[1] Sarah Chen — Salary: £120000.00 | Total: £144000.00
=== Team Report: Sarah Chen ===
  [10] James Liu — Salary: £65000.00 | Total: £65000.00
  [11] Aisha Patel — Salary: £68000.00 | Total: £68000.00
  Team size: 2
Sarah Chen total: £144000.0
  Bonus: £24000.0
Fatima Hassan total: £72000.0
```

---

## Chapter Summary

| Concept             | Key Points                                                                  |
|---------------------|-----------------------------------------------------------------------------|
| Class / Object      | Blueprint / instance. Use `new` to create. Fields store state.              |
| Access modifiers    | `private` by default for fields. Expose via public methods.                 |
| Constructor         | Same name as class, no return type. Use `this()` to chain constructors.     |
| Getters / Setters   | Naming: `get/is` + field name, `set` + field name. Add validation in setters. |
| `this`              | Refers to current object. Use to disambiguate fields from params.           |
| `extends`           | Single inheritance. Subclass inherits non-private members.                  |
| `@Override`         | Always annotate overridden methods. Compiler validates correctness.         |
| `super`             | Access parent constructor or methods. `super()` must be first in constructor. |
| Interface           | Pure contract. `implements`. A class can implement multiple interfaces.     |
| Polymorphism        | Parent-type reference holds child object. Correct method called at runtime. |
| `instanceof`        | Type check. Use `if (x instanceof Dog) { Dog d = (Dog) x; ... }` to cast safely. |

> **Spring Boot connection:** Every Spring service, repository, and controller you write will use interfaces and dependency injection. The patterns you practiced in this chapter are the direct foundation of everything that comes next.
