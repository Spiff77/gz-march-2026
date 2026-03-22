# Lab 1 — Java Basics

**Estimated time:** 60 minutes
**Covers:** Chapter 1 — Variables, types, Strings, control flow, methods, arrays

---

## Setup Instructions

Open the lab folder in **IntelliJ IDEA**:

1. Open IntelliJ IDEA
2. Click **File → Open** and select the `resources/` folder of this lab (the one containing the `src/` directory)
3. IntelliJ will detect it as a project — wait for indexing to complete
4. Navigate to `src/main/java/io/treelevel/training/exercises/` in the Project panel
5. Open an exercise file and click the green **▶** arrow in the gutter next to `main` to run it

---

## Exercise 1 — FizzBuzz

### Description

Write a method `fizzBuzz(int n)` that returns:
- `"FizzBuzz"` if n is divisible by both 3 and 5
- `"Fizz"` if n is divisible by 3
- `"Buzz"` if n is divisible by 5
- The number as a String otherwise

Then call it in a loop from 1 to 20.

### Expected Output

```
1
2
Fizz
4
Buzz
Fizz
7
8
Fizz
Buzz
11
Fizz
13
14
FizzBuzz
16
17
Fizz
19
Buzz
```

### Hints

- Use the modulo operator `%` to check divisibility: `n % 3 == 0`
- Check `% 15 == 0` (divisible by both) **before** `% 3` and `% 5`
- `String.valueOf(n)` converts an integer to a String

### Starter Code

Open `Exercise1_FizzBuzz.java` and implement `fizzBuzz(int n)`.

---

## Exercise 2 — Palindrome Checker

### Description

Write a method `isPalindrome(String s)` that returns `true` if the string reads the same forwards and backwards, `false` otherwise.

Rules:
- Case-insensitive: `"Racecar"` is a palindrome
- Ignore spaces: `"race car"` is treated as `"racecar"`

### Expected Output

```
true   // "racecar"
true   // "Racecar"
true   // "race car"
false  // "hello"
true   // "level"
false  // "Training"
```

### Hints

- Normalize first: `s.toLowerCase().replace(" ", "")`
- Build the reversed string with a loop: iterate from the last character to the first, adding each with `+=`
- Compare with `.equals()`

### Starter Code

Open `Exercise2_Palindrome.java` and implement `isPalindrome(String s)`.

---

## Exercise 3 — Word Counter

### Description

Write a method `countWords(String sentence)` that returns the number of words in a sentence. Words are separated by spaces.

### Expected Output

```
4   // "The quick brown fox"
2   // "  hello   world  "
0   // ""
1   // "Java"
```

### Hints

- `sentence.trim()` removes leading/trailing spaces
- `sentence.trim().isEmpty()` checks if the string is blank — return 0 in that case
- `sentence.trim().split("\\s+")` splits on one or more spaces

### Starter Code

Open `Exercise3_WordCount.java` and implement `countWords(String sentence)`.

---

## Exercise 4 — Temperature Converter

### Description

Write two methods to convert temperatures:
1. `celsiusToFahrenheit(double celsius)` — formula: `(C × 9/5) + 32`
2. `fahrenheitToCelsius(double fahrenheit)` — formula: `(F - 32) × 5/9`

### Expected Output

```
32.0    // 0°C
212.0   // 100°C
-40.0   // -40°C (same in both!)
0.0     // 32°F
37.0    // 98.6°F (body temperature)
```

### Hints

- Integer division pitfall: `5/9` evaluates to `0` in Java — use `5.0/9` instead
- Round to 1 decimal: `Math.round(result * 10.0) / 10.0`

### Starter Code

Open `Exercise4_TempConverter.java` and implement both methods.

---

## Exercise 5 — Grade Calculator

### Description

Write two methods:
1. `calculateAverage(int[] scores)` — returns the average of the array as a `double`
2. `getLetterGrade(double average)` — returns the letter grade

Grading scale:

| Average | Grade |
|---------|-------|
| 90–100  | A     |
| 80–89   | B     |
| 70–79   | C     |
| 60–69   | D     |
| 0–59    | F     |

### Expected Output

```
Alice: B   // scores: 85, 92, 78, 95, 88  → avg 87.6
Bob:   F   // scores: 55, 60, 45, 72, 50  → avg 56.4
Eva:   A   // scores: 100, 98, 97, 99, 100 → avg 98.8
```

### Hints

- Sum the array with a `for-each` loop, accumulate into a `double`
- Use `if / else if` chains for the grade thresholds

### Starter Code

Open `Exercise5_GradeCalc.java` and implement both methods.

---

## Solutions

<details>
<summary><strong>Click to reveal solutions (try the exercises first!)</strong></summary>

### Solution 1 — FizzBuzz

```java
public static String fizzBuzz(int n) {
    if (n % 15 == 0) return "FizzBuzz";
    if (n % 3 == 0)  return "Fizz";
    if (n % 5 == 0)  return "Buzz";
    return String.valueOf(n);
}
```

---

### Solution 2 — Palindrome Checker

```java
public static boolean isPalindrome(String s) {
    String normalized = s.toLowerCase().replace(" ", "");
    String reversed = "";
    for (int i = normalized.length() - 1; i >= 0; i--) {
        reversed += normalized.charAt(i);
    }
    return normalized.equals(reversed);
}
```

---

### Solution 3 — Word Counter

```java
public static int countWords(String sentence) {
    if (sentence == null || sentence.trim().isEmpty()) {
        return 0;
    }
    return sentence.trim().split("\\s+").length;
}
```

---

### Solution 4 — Temperature Converter

```java
public static double celsiusToFahrenheit(double celsius) {
    return Math.round(((celsius * 9.0 / 5.0) + 32) * 10.0) / 10.0;
}

public static double fahrenheitToCelsius(double fahrenheit) {
    return Math.round(((fahrenheit - 32) * 5.0 / 9.0) * 10.0) / 10.0;
}
```

---

### Solution 5 — Grade Calculator

```java
public static double calculateAverage(int[] scores) {
    double sum = 0;
    for (int score : scores) {
        sum += score;
    }
    return sum / scores.length;
}

public static String getLetterGrade(double average) {
    if (average >= 90) return "A";
    if (average >= 80) return "B";
    if (average >= 70) return "C";
    if (average >= 60) return "D";
    return "F";
}
```

</details>
