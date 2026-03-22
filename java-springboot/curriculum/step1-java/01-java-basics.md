# Chapter 1 — Java Basics

---

## Learning Objectives

By the end of this chapter, you will be able to:

- Explain Java's core design principles: static typing, compilation, and explicit structure
- Declare and use variables with Java's primitive types
- Work with `String` and its most common methods
- Write control flow structures: `if/else`, `switch`, `for`, `while`, `for-each`
- Define and call methods, including overloaded methods
- Declare and iterate over arrays
- Understand `null` and how to handle it safely

---

## 1. Java's Core Design Principles

Java is designed for large teams, long-lived codebases, and production systems where ambiguity is expensive. Three principles shape everything you will write.

### 1.1 Static Typing

In Java, every variable has a fixed type declared at compile time. Once declared, it can never hold a value of a different type:

```java
// Java — static typing
int x = 10;
x = "hello"; // COMPILE ERROR — incompatible types
```

This means an entire class of bugs is caught before your code ever runs. Your IDE will flag type mismatches in real time, and the compiler confirms correctness before deployment.

### 1.2 Compilation

Java source files (`.java`) are compiled into bytecode (`.class` files) by `javac`, then executed by the JVM (Java Virtual Machine). Spring Boot handles this for you, but it is worth understanding the pipeline:

```
YourCode.java  →  javac  →  YourCode.class  →  JVM  →  Running program
```

### 1.3 Explicit Structure

Java requires more structure than many languages. Every piece of code lives inside a class. Every file has a declared `package`. Types are explicit everywhere. This is intentional — readability and maintainability at scale matter more than terseness.

```java
// The minimum Java program
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Training!");
    }
}
```

You will get used to the structure quickly. Spring Boot further reduces boilerplate with annotations, which you will see in later chapters.

---

## 2. Variables and Primitive Types

Java has two categories of types: **primitive types** (built into the language, stored by value) and **reference types** (objects, stored by reference). Let's start with primitives.

### 2.1 Primitive Type Reference Table

| Java Type  | Size     | Range / Notes                          |
|------------|----------|----------------------------------------|
| `byte`     | 8 bits   | -128 to 127                            |
| `short`    | 16 bits  | -32,768 to 32,767                      |
| `int`      | 32 bits  | -2,147,483,648 to 2,147,483,647        |
| `long`     | 64 bits  | Very large integers, suffix `L`        |
| `float`    | 32 bits  | Decimal, less precise, suffix `f`      |
| `double`   | 64 bits  | Decimal, more precise — default choice |
| `boolean`  | 1 bit    | `true` or `false` (lowercase!)         |
| `char`     | 16 bits  | Single Unicode character, single quotes|

In practice, you will reach for `int` for whole numbers, `double` for decimals, `boolean` for flags, and `String` for text. The others appear when precision or memory constraints matter.

### 2.2 Variable Declaration and Assignment

```java
// Declaration with immediate assignment (most common)
int employeeId = 1042;
long accountNumber = 9876543210L;   // note the L suffix for long literals
double interestRate = 0.0375;
float taxRate = 0.2f;               // note the f suffix for float literals
boolean isActive = true;
char grade = 'A';                   // single quotes for char

// Declaration without assignment (variable is "uninitialized")
int counter;
counter = 0; // must assign before use — compiler enforces this

// Multiple declarations of the same type (use sparingly for readability)
int x = 0, y = 0, z = 0;
```

### 2.3 Type Casting

```java
// Widening cast — automatic, no data loss
int i = 42;
long l = i;       // int → long, automatic
double d = i;     // int → double, automatic

// Narrowing cast — explicit, potential data loss
double pi = 3.14159;
int truncated = (int) pi;  // truncated = 3, decimal part is dropped

// Common pitfall: integer division
int a = 7;
int b = 2;
double result = a / b;          // result = 3.0  !! integer division first
double correct = (double) a / b; // correct = 3.5
```

### 2.4 Constants

Use `final` to declare a constant — the compiler prevents reassignment:

```java
final double ANNUAL_INTEREST_RATE = 0.045;
final int MAX_LOGIN_ATTEMPTS = 3;

ANNUAL_INTEREST_RATE = 0.05; // COMPILE ERROR — cannot reassign a final variable
```

By convention, constant names use `ALL_CAPS_WITH_UNDERSCORES`. Unlike a naming convention, `final` is enforced — the compiler refuses to compile any code that tries to change the value.

---

## 3. The String Type

`String` is not a primitive — it is a class (a reference type). However, Java gives it special treatment so it feels almost primitive to use. Key fact: **Strings in Java are immutable**. Every method that appears to modify a String actually returns a new one.

### 3.1 Creating Strings

```java
String firstName = "Ada";
String lastName = "Lovelace";

// Concatenation with +
String fullName = firstName + " " + lastName;  // "Ada Lovelace"

// String.format — clean and readable for complex strings
String message = String.format("Welcome, %s. Your ID is %d.", fullName, 1042);

// Java 15+ text blocks (multi-line strings)
String json = """
        {
            "name": "Ada Lovelace",
            "role": "Engineer"
        }
        """;
```

### 3.2 Key String Methods

```java
String s = "Hello, Training!";

// Length
int len = s.length();              // 16

// Character access
char c = s.charAt(0);              // 'H'

// Substring
String sub = s.substring(7);      // "Training!"
String sub2 = s.substring(7, 15); // "Training"

// Search
boolean found = s.contains("Training");   // true
int idx = s.indexOf("Training");          // 7
boolean starts = s.startsWith("Hello");   // true

// Case
String upper = s.toUpperCase();    // "HELLO, TRAINING!"
String lower = s.toLowerCase();    // "hello, training!"

// Trim whitespace
String padded = "  hello  ";
String trimmed = padded.trim();    // "hello"

// Split
String csv = "Alice,Bob,Charlie";
String[] names = csv.split(",");   // ["Alice", "Bob", "Charlie"]

// Replace
String replaced = s.replace("Training", "World"); // "Hello, World!"

// Check empty/blank
String empty = "";
boolean isEmpty = empty.isEmpty();   // true
boolean isBlank = "   ".isBlank();   // true (Java 11+, ignores whitespace)
```

### 3.3 CRITICAL: equals() vs ==

This is one of the most common bugs for developers new to Java.

```java
String a = "hello";
String b = "hello";
String c = new String("hello");

// == compares REFERENCES (memory addresses), not content
System.out.println(a == b);      // true (due to string pool — unreliable)
System.out.println(a == c);      // FALSE — different objects in memory!

// .equals() compares CONTENT — always use this for Strings
System.out.println(a.equals(b)); // true
System.out.println(a.equals(c)); // true

// Safe pattern when one side might be a variable (avoids NullPointerException)
String input = getUserInput(); // might return null
if ("expected".equals(input)) { // put the literal first
    // safe — no NPE even if input is null
}
```

> **Rule:** Always use `.equals()` (or `.equalsIgnoreCase()`) to compare String values. Reserve `==` for primitives.

---

## 4. Control Flow

### 4.1 if / else if / else

Java uses braces `{}` to delimit blocks:

```java
int score = 78;

if (score >= 90) {
    System.out.println("Grade: A");
} else if (score >= 80) {
    System.out.println("Grade: B");
} else if (score >= 70) {
    System.out.println("Grade: C");
} else {
    System.out.println("Grade: F");
}

// Ternary operator — concise single-expression if/else
String status = score >= 50 ? "PASS" : "FAIL";
```

### 4.2 switch

```java
// Traditional switch (Java 1+)
String dayOfWeek = "MONDAY";

switch (dayOfWeek) {
    case "MONDAY":
    case "TUESDAY":
    case "WEDNESDAY":
    case "THURSDAY":
    case "FRIDAY":
        System.out.println("Weekday — markets open");
        break; // IMPORTANT: without break, execution falls through to next case
    case "SATURDAY":
    case "SUNDAY":
        System.out.println("Weekend — markets closed");
        break;
    default:
        System.out.println("Unknown day");
}

// Modern switch expression (Java 14+) — cleaner, no fall-through risk
String message = switch (dayOfWeek) {
    case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
    case "SATURDAY", "SUNDAY" -> "Weekend";
    default -> "Unknown";
};
```

### 4.3 for Loop

```java
// Standard for loop
for (int i = 0; i < 10; i++) {
    System.out.println("Iteration: " + i);
}

// Counting down
for (int i = 10; i > 0; i--) {
    System.out.println("Countdown: " + i);
}

// Step size
for (int i = 0; i <= 100; i += 10) {
    System.out.println(i); // 0, 10, 20, ... 100
}
```

### 4.4 while and do-while

```java
// while loop — checks condition before each iteration
int attempts = 0;
while (attempts < 3) {
    System.out.println("Attempt " + (attempts + 1));
    attempts++;
}

// do-while — executes body at least once, then checks condition
int pin;
do {
    pin = promptUserForPin(); // hypothetical method
} while (pin < 1000 || pin > 9999); // repeat until 4-digit PIN entered
```

### 4.5 for-each (Enhanced for Loop)

The most common loop in real Java code — use it whenever you just need to iterate:

```java
int[] scores = {85, 92, 78, 95, 88};

for (int score : scores) {
    System.out.println("Score: " + score);
}

String[] currencies = {"GBP", "USD", "EUR", "HKD"};
for (String currency : currencies) {
    System.out.println("Currency: " + currency);
}
```

### 4.6 break and continue

```java
// break — exit the loop entirely
for (int i = 0; i < 100; i++) {
    if (i == 5) {
        break; // stops at 5
    }
    System.out.println(i);
}

// continue — skip current iteration, continue the loop
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) {
        continue; // skip even numbers
    }
    System.out.println(i); // prints 1, 3, 5, 7, 9
}
```

---

## 5. Methods

A method is a named block of code that performs a task and optionally returns a value. Every method must belong to a class.

### 5.1 Method Syntax

```java
// Structure:
// [access modifier] [static?] returnType methodName(parameters) { body }

public static int add(int a, int b) {
    return a + b;
}

public static void printSeparator() { // void = no return value
    System.out.println("-------------------");
}
```

Key points:
- The return type comes before the method name. Use `void` when the method produces no value.
- Parameters must declare their type. Java will not accept an argument of the wrong type.
- `static` means the method belongs to the class itself, not to an instance of it. You call it without creating an object: `ClassName.methodName(args)`.

### 5.2 Practical Method Examples

```java
public class MathUtils {

    // Returns the larger of two numbers
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    // Checks whether a number is prime
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Computes compound interest
    public static double compoundInterest(double principal, double rate, int years) {
        return principal * Math.pow(1 + rate, years);
    }
}

// Calling the methods
int bigger = MathUtils.max(42, 17);                      // 42
boolean prime = MathUtils.isPrime(97);                   // true
double amount = MathUtils.compoundInterest(10000, 0.05, 10); // 16288.94...
```

### 5.3 Method Overloading

Java allows multiple methods with the same name as long as their parameter lists differ. The compiler picks the right one based on the argument types at the call site.

```java
public class Logger {

    // Overloaded log() methods
    public static void log(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void log(String message, String level) {
        System.out.println("[" + level + "] " + message);
    }

    public static void log(String message, String level, int errorCode) {
        System.out.println("[" + level + "] " + message + " (code: " + errorCode + ")");
    }
}

// Usage
Logger.log("Server started");                        // [INFO] Server started
Logger.log("Payment failed", "WARN");               // [WARN] Payment failed
Logger.log("DB unreachable", "ERROR", 503);         // [ERROR] DB unreachable (code: 503)
```

Overloading lets you use one consistent name (`log`) for related operations that differ in what they accept. The compiler resolves which version to call based on what arguments you pass.

---

## 6. Arrays

An array in Java is a fixed-size, ordered collection of elements of the same type. Arrays cannot grow or shrink after creation. When you need a resizable collection, use `ArrayList` (covered in Chapter 3).

### 6.1 Declaration and Initialization

```java
// Style 1: declare size, fill later
int[] scores = new int[5];       // [0, 0, 0, 0, 0] — default values
scores[0] = 85;
scores[1] = 92;
scores[2] = 78;

// Style 2: array literal (most concise)
int[] scores = {85, 92, 78, 95, 88};

// String array
String[] currencies = {"GBP", "USD", "EUR", "HKD", "JPY"};

// 2D array (matrix)
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

### 6.2 Accessing and Iterating

```java
int[] scores = {85, 92, 78, 95, 88};

// Access by index (zero-based)
System.out.println(scores[0]); // 85
System.out.println(scores[4]); // 88

// Length property (no parentheses — it's a field, not a method)
System.out.println(scores.length); // 5

// Iterate with index
for (int i = 0; i < scores.length; i++) {
    System.out.println("Score " + i + ": " + scores[i]);
}

// Iterate without index (cleaner when you don't need i)
for (int score : scores) {
    System.out.println("Score: " + score);
}

// Calculate average
int sum = 0;
for (int score : scores) {
    sum += score;
}
double average = (double) sum / scores.length; // 87.6
```

### 6.3 Arrays Utility Class

```java
import java.util.Arrays;

int[] data = {5, 2, 8, 1, 9, 3};

Arrays.sort(data);                         // sorts in-place: [1, 2, 3, 5, 8, 9]
System.out.println(Arrays.toString(data)); // "[1, 2, 3, 5, 8, 9]" — readable print
int idx = Arrays.binarySearch(data, 8);    // 4 (index after sort)
```

> **Note:** For dynamic, resizable collections, Java uses `ArrayList` and `List` — you will cover those in Chapter 3 (Collections).

---

## 7. null

`null` represents the absence of a value for any reference type (objects, Strings, arrays). Primitives cannot be `null` — only reference types can.

```java
public class NullExamples {
    public static void main(String[] args) {
        String name = null;       // name holds no value
        int[] data = null;        // data points to no array

        // Checking for null — use == for null checks (not .equals()!)
        if (name == null) {
            System.out.println("Name not provided");
        }

        // The NullPointerException (NPE) — Java's most common runtime error
        String s = null;
        // s.length(); // throws NullPointerException at runtime — can't call methods on null

        // Safe pattern: always guard before using a potentially null value
        if (s != null) {
            System.out.println(s.length());
        }
    }
}
```

> **Key point:** Calling any method on `null` causes a `NullPointerException`. This is the most common runtime error in Java. Null safety — checking before you use — is a core discipline in Java development.

---

## 8. Printing Output

```java
// println — prints with a newline at the end
System.out.println("Hello");
System.out.println(42);
System.out.println(3.14);
System.out.println(true);

// print — prints WITHOUT a trailing newline
System.out.print("Hello ");
System.out.print("World");           // same line: "Hello World"

// printf — formatted output
System.out.printf("%-15s %8.2f%n", "Interest rate:", 0.045);
// Output: "Interest rate:      0.05"
// %n is the cross-platform newline (prefer over \n in printf)

// String.format — build formatted strings
String report = String.format("Account %d | Balance: £%.2f", 1042, 15230.50);
System.out.println(report);
// Output: Account 1042 | Balance: £15230.50
```

Common format specifiers:
- `%s` — String
- `%d` — integer
- `%f` — floating-point (`%.2f` = 2 decimal places)
- `%n` — newline (cross-platform)
- `%-15s` — left-aligned String in a field 15 characters wide

---

## Chapter Summary

| Concept               | Key points                                                            |
|-----------------------|-----------------------------------------------------------------------|
| Static typing         | Every variable has a declared type. The compiler enforces it.         |
| Primitive types       | `int`, `long`, `double`, `boolean`, `char` — stored by value         |
| `String`              | Reference type. Immutable. Use `.equals()` for comparisons. Use `==` for null checks. |
| `null`                | Absence of a value. Calling methods on `null` → `NullPointerException` |
| Control flow          | `if/else`, `switch`, `for`, `while`, `do-while`, `for-each`          |
| Methods               | Declared with return type, name, and typed parameters                 |
| Method overloading    | Same name, different parameter lists — compiler selects the right one |
| Arrays                | Fixed-size, same type. Access via `arr[i]`. Length via `arr.length`. |
| Constants             | `final` keyword — compiler prevents reassignment                      |
| Formatted output      | `String.format()`, `System.out.printf()`, text blocks (Java 15+)     |

> **Coming up in Chapter 2:** Object-Oriented Programming — classes, inheritance, interfaces, and the patterns that make Spring Boot work.

---

## Reference — printf & Formatted Output

> This section is reference material — the instructor may skip it during the demo. Read it independently.

The following example brings together variables, arrays, loops, methods, and formatted output in a single program. It is a good reference for how `printf` column formatting works.

```java
public class SalesReport {

    public static void main(String[] args) {
        String[] regions = {"London", "Hong Kong", "New York", "Singapore"};
        double[] revenues = {1_250_000.50, 980_000.00, 1_540_000.75, 720_000.25};

        System.out.println("=== Q1 Sales Report ===");
        System.out.printf("%-15s %15s%n", "Region", "Revenue (USD)");
        System.out.println("-".repeat(32));

        double total = 0;
        String topRegion = null;
        double topRevenue = 0;

        for (int i = 0; i < regions.length; i++) {
            System.out.printf("%-15s $%,14.2f%n", regions[i], revenues[i]);
            total += revenues[i];

            if (revenues[i] > topRevenue) {
                topRevenue = revenues[i];
                topRegion = regions[i];
            }
        }

        System.out.println("-".repeat(32));
        System.out.printf("%-15s $%,14.2f%n", "TOTAL", total);
        System.out.printf("%nTop performing region: %s ($%,.2f)%n", topRegion, topRevenue);
        System.out.println("Performance: " + getPerformanceLabel(topRevenue));
    }

    public static String getPerformanceLabel(double revenue) {
        if (revenue >= 1_000_000) {
            return "HIGH";
        } else if (revenue >= 500_000) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }
}
```

Expected output:
```
=== Q1 Sales Report ===
Region           Revenue (USD)
--------------------------------
London            $1,250,000.50
Hong Kong           $980,000.00
New York          $1,540,000.75
Singapore           $720,000.25
--------------------------------
TOTAL             $4,490,001.50

Top performing region: New York ($1,540,000.75)
Performance: HIGH
```
