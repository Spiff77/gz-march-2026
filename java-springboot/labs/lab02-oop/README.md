# Lab 2 — OOP in Java

**Estimated time:** 90 minutes
**Covers:** Chapter 2 — Classes, constructors, access modifiers, getters/setters, inheritance, interfaces, polymorphism

---

## Setup

Open the lab folder in **IntelliJ IDEA**:

1. Open IntelliJ IDEA
2. Click **File → Open** and select the `resources/` folder of this lab
3. Navigate to `src/main/java/io/treelevel/training/exercises/` in the Project panel
4. Create new Java class files for each exercise, or open the provided starters

> Each exercise uses multiple classes. Place each public class in its own `.java` file in the same package.

---

## Exercise 1 — BankAccount

### Description

Model a simple bank account. This exercise focuses on:
- Private fields and getters/setters
- Business logic and validation inside methods

Implement a `BankAccount` class with:

**Fields (all private):**
- `owner` (String)
- `balance` (double) — starts at 0

**Constructor:** `BankAccount(String owner)`

**Methods:**
- `deposit(double amount)` — adds to balance. Print an error and return if amount <= 0.
- `withdraw(double amount)` — deducts from balance. Print an error and return if amount <= 0 or amount > balance.
- `getBalance()`, `getOwner()`
- `toString()` — e.g. `"Alice — Balance: £1700.00"`

### Expected Output

```
Alice — Balance: £0.00
Deposited £2000.00 → Balance: £2000.00
Withdrew £300.00 → Balance: £1700.00
ERROR: Insufficient funds.
ERROR: Deposit amount must be positive.
```

### Hints

- Use `System.out.println("ERROR: ...")` and `return` early when validation fails
- `System.out.printf("£%.2f", amount)` formats to 2 decimal places

### Starter Code

Open `ex1/BankAccount.java` and implement the class. The `main` method is in `Main.java`.

---

## Exercise 2 — Animal Interface

### Description

Design an interface-based animal hierarchy. This exercise focuses on:
- Defining an interface as a contract
- Implementing one interface across multiple unrelated classes
- Polymorphism — working with a mix of animals through the interface type

**Define an interface `Animal` with:**
- `String getName()` — returns the animal's name
- `String speak()` — returns the sound the animal makes
- `String move()` — returns how the animal moves (e.g., "runs", "swims", "flies")
- `String describe()` — a default method that builds a sentence: `"[Name] the [type] says '[sound]' and [moves]."`

**Implement three classes:**

| Class  | `speak()`    | `move()`           | Extra field  |
|--------|--------------|--------------------|--------------|
| `Dog`  | `"Woof!"`    | `"runs on 4 legs"` | `breed`      |
| `Cat`  | `"Meow!"`    | `"prowls silently"`| `isIndoor`   |
| `Bird` | `"Tweet!"`   | `"flies through the air"` | `wingspan` (double, in cm) |

Each class should have a constructor, getters for extra fields, and an appropriate `toString()`.

### Expected Output

```
Rex the Dog says 'Woof!' and runs on 4 legs.
Whiskers the Cat says 'Meow!' and prowls silently.
Tweety the Bird says 'Tweet!' and flies through the air.

--- All animals speaking ---
Rex: Woof!
Whiskers: Meow!
Tweety: Tweet!

--- Dog details ---
Name: Rex
Breed: Labrador
Speaks: Woof!

--- Bird details ---
Name: Tweety
Wingspan: 35.5 cm
```

### Hints

- The `describe()` method in the interface should use `getClass().getSimpleName()` to get the runtime class name (e.g., "Dog")
- Create an array or loop over `Animal[]` to demonstrate polymorphism — all three classes work through the interface reference type
- The `isIndoor` field in `Cat` is a `boolean` — its getter should be named `isIndoor()` (not `getIsIndoor()`)

### Starter Code

```java
public class Exercise2 {

    public static void main(String[] args) {
        Dog dog    = new Dog("Rex", "Labrador");
        Cat cat    = new Cat("Whiskers", true);
        Bird bird  = new Bird("Tweety", 35.5);

        // Test describe() for each
        System.out.println(dog.describe());
        System.out.println(cat.describe());
        System.out.println(bird.describe());

        System.out.println("\n--- All animals speaking ---");
        Animal[] animals = {dog, cat, bird};
        for (Animal animal : animals) {
            System.out.println(animal.getName() + ": " + animal.speak());
        }

        System.out.println("\n--- Dog details ---");
        System.out.println("Name: " + dog.getName());
        System.out.println("Breed: " + dog.getBreed());
        System.out.println("Speaks: " + dog.speak());

        System.out.println("\n--- Bird details ---");
        System.out.println("Name: " + bird.getName());
        System.out.printf("Wingspan: %.1f cm%n", bird.getWingspan());
    }
}

interface Animal {
    // Your interface methods here
}

class Dog implements Animal {
    // Your code here
}

class Cat implements Animal {
    // Your code here
}

class Bird implements Animal {
    // Your code here
}
```

---

## Exercise 3 — Employee and Manager

### Description

Build a simple employee hierarchy using inheritance. This exercise focuses on:
- Extending a class with `extends`
- Overriding methods with `@Override`
- Polymorphism and `instanceof`

**`Employee` class is provided** — read it but do not modify it. It has:
- Fields: `id`, `name`, `annualSalary`
- `monthlySalary()` — returns `annualSalary / 12`
- `toString()` — formatted summary

**Implement `Manager extends Employee`:**
- Additional field: `bonusRate` (double, e.g. `0.20` = 20%)
- Constructor: `Manager(int id, String name, double annualSalary, double bonusRate)`
- `double calculateBonus()` — returns `annualSalary × bonusRate`
- Override `toString()` to add the bonus to the summary

### Expected Output

```
[1] Sarah Chen — £120,000/yr | Monthly: £10,000.00
  Bonus: £24,000.00
[2] James Liu — £65,000/yr | Monthly: £5,416.67
[3] Aisha Patel — £68,000/yr | Monthly: £5,666.67
```

### Hints

- Call `super(id, name, annualSalary)` as the first line of the Manager constructor
- `calculateBonus()` can use `getAnnualSalary()` (inherited getter)
- Use `instanceof` to detect managers: `if (emp instanceof Manager) { Manager mgr = (Manager) emp; ... }`

### Starter Code

Open `ex4/Manager.java` and implement the class. `Employee.java` and `Main.java` are already provided.

---

## Solutions

<details>
<summary><strong>Click to reveal solutions (attempt the exercises first!)</strong></summary>

### Solution 1 — BankAccount

```java
class BankAccount {

    private final String owner;
    private double balance;

    public BankAccount(String owner) {
        this.owner = owner;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("ERROR: Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("Deposited £%.2f → Balance: £%.2f%n", amount, balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("ERROR: Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("ERROR: Insufficient funds.");
            return;
        }
        balance -= amount;
        System.out.printf("Withdrew £%.2f → Balance: £%.2f%n", amount, balance);
    }

    public double getBalance() { return balance; }
    public String getOwner()   { return owner; }

    @Override
    public String toString() {
        return String.format("%s — Balance: £%.2f", owner, balance);
    }
}
```

---

### Solution 2 — Animal Interface

```java
public class Exercise2 {

    public static void main(String[] args) {
        Dog  dog  = new Dog("Rex", "Labrador");
        Cat  cat  = new Cat("Whiskers", true);
        Bird bird = new Bird("Tweety", 35.5);

        System.out.println(dog.describe());
        System.out.println(cat.describe());
        System.out.println(bird.describe());

        System.out.println("\n--- All animals speaking ---");
        Animal[] animals = {dog, cat, bird};
        for (Animal animal : animals) {
            System.out.println(animal.getName() + ": " + animal.speak());
        }

        System.out.println("\n--- Dog details ---");
        System.out.println("Name: " + dog.getName());
        System.out.println("Breed: " + dog.getBreed());
        System.out.println("Speaks: " + dog.speak());

        System.out.println("\n--- Bird details ---");
        System.out.println("Name: " + bird.getName());
        System.out.printf("Wingspan: %.1f cm%n", bird.getWingspan());
    }
}

interface Animal {
    String getName();
    String speak();
    String move();

    default String describe() {
        return String.format("%s the %s says '%s' and %s.",
                getName(), getClass().getSimpleName(), speak(), move());
    }
}

class Dog implements Animal {
    private final String name;
    private final String breed;

    public Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    @Override public String getName()  { return name; }
    @Override public String speak()    { return "Woof!"; }
    @Override public String move()     { return "runs on 4 legs"; }

    public String getBreed() { return breed; }
}

class Cat implements Animal {
    private final String name;
    private final boolean indoor;

    public Cat(String name, boolean indoor) {
        this.name = name;
        this.indoor = indoor;
    }

    @Override public String getName()  { return name; }
    @Override public String speak()    { return "Meow!"; }
    @Override public String move()     { return "prowls silently"; }

    public boolean isIndoor() { return indoor; }
}

class Bird implements Animal {
    private final String name;
    private final double wingspan;

    public Bird(String name, double wingspan) {
        this.name = name;
        this.wingspan = wingspan;
    }

    @Override public String getName()  { return name; }
    @Override public String speak()    { return "Tweet!"; }
    @Override public String move()     { return "flies through the air"; }

    public double getWingspan() { return wingspan; }
}
```

---

### Solution 3 — Employee and Manager

```java
// Manager.java — Employee.java is provided
class Manager extends Employee {

    private final double bonusRate;

    public Manager(int id, String name, double annualSalary, double bonusRate) {
        super(id, name, annualSalary);
        this.bonusRate = bonusRate;
    }

    public double calculateBonus() {
        return getAnnualSalary() * bonusRate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Bonus: £%,.2f", calculateBonus());
    }
}
```

</details>
