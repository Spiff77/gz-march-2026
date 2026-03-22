package io.treelevel.training.ex3;

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
