package io.treelevel.training.ex1;

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
