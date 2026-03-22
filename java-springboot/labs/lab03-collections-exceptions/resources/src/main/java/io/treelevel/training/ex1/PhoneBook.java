package io.treelevel.training.ex1;

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
