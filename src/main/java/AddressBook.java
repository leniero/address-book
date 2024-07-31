import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The AddressBook class provides functionalities to manage contacts.
 */
public class AddressBook {
    private List<Contact> contacts;

    /**
     * Constructs an AddressBook with an empty list of contacts.
     */
    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    /**
     * Adds a contact to the address book if it is not a duplicate.
     *
     * @param contact the contact to add
     * @throws IllegalArgumentException if the contact is a duplicate
     */
    public void addContact(Contact contact) {
        validateContact(contact);
        if (!isDuplicate(contact)) {
            contacts.add(contact);
        } else {
            throw new IllegalArgumentException("Duplicate contact information.");
        }
    }

    /**
     * Returns all contacts in the address book.
     *
     * @return a list of all contacts
     */
    public List<Contact> getAllContacts() {
        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getName))
                .collect(Collectors.toList());
    }

    /**
     * Searches for contacts by name.
     *
     * @param name the name to search for
     * @return a list of contacts that match the name
     */
    public List<Contact> searchContactByName(String name) {
        return contacts.stream()
                .filter(contact -> contact.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Searches for contacts by phone number.
     *
     * @param phoneNumber the phone number to search for
     * @return a list of contacts that match the phone number
     */
    public List<Contact> searchContactByPhoneNumber(String phoneNumber) {
        return contacts.stream()
                .filter(contact -> contact.getPhoneNumber().contains(phoneNumber))
                .collect(Collectors.toList());
    }

    /**
     * Searches for contacts by email address.
     *
     * @param emailAddress the email address to search for
     * @return a list of contacts that match the email address
     */
    public List<Contact> searchContactByEmailAddress(String emailAddress) {
        return contacts.stream()
                .filter(contact -> contact.getEmailAddress().contains(emailAddress))
                .collect(Collectors.toList());
    }

    /**
     * Searches for contacts by name, phone number, or email address and returns results in alphabetical order.
     *
     * @param query the query to search for
     * @return a list of contacts that match the query, sorted in alphabetical order
     */
    public List<Contact> searchContactsSorted(String query) {
        return contacts.stream()
                .filter(contact -> contact.getName().toLowerCase().contains(query.toLowerCase()) ||
                        contact.getPhoneNumber().equals(query) ||
                        contact.getEmailAddress().equals(query))
                .sorted(Comparator.comparing(Contact::getName))
                .collect(Collectors.toList());
    }

    /**
     * Deletes all contacts from the address book.
     *
     * @param confirm if true, deletes all contacts; if false, does nothing
     */
    public void deleteAllContacts(boolean confirm) {
        if (confirm) {
            contacts.clear();
        }
    }

    /**
     * Removes a contact by name.
     *
     * @param name the name of the contact to remove
     * @return true if the contact was found and removed, false otherwise
     */
    public boolean removeContact(String name) {
        return contacts.removeIf(contact -> contact.getName().equals(name));
    }

    /**
     * Edits a contact's details by phone number.
     *
     * @param phoneNumber the phone number of the contact to edit
     * @param newName the new name of the contact
     * @param newEmailAddress the new email address of the contact
     * @return true if the contact was found and updated, false otherwise
     */
    public boolean editContact(String phoneNumber, String newName, String newEmailAddress) {
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                contact.setName(newName);
                contact.setEmailAddress(newEmailAddress);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given contact is a duplicate.
     *
     * @param contact the contact to check
     * @return true if the contact is a duplicate, false otherwise
     */
    public boolean isDuplicate(Contact contact) {
        return contacts.stream().anyMatch(existingContact ->
                existingContact.getPhoneNumber().equals(contact.getPhoneNumber()) ||
                        existingContact.getEmailAddress().equals(contact.getEmailAddress())
        );
    }

    /**
     * Checks if the given name is a duplicate.
     *
     * @param name the name to check
     * @return true if the name is a duplicate, false otherwise
     */
    public boolean isNameDuplicate(String name) {
        return contacts.stream().anyMatch(existingContact ->
                existingContact.getName().equalsIgnoreCase(name)
        );
    }

    /**
     * Checks if the given phone number is a duplicate.
     *
     * @param phoneNumber the phone number to check
     * @return true if the phone number is a duplicate, false otherwise
     */
    public boolean isPhoneNumberDuplicate(String phoneNumber) {
        return contacts.stream().anyMatch(existingContact ->
                existingContact.getPhoneNumber().equals(phoneNumber)
        );
    }

    /**
     * Checks if the given email address is a duplicate.
     *
     * @param emailAddress the email address to check
     * @return true if the email address is a duplicate, false otherwise
     */
    public boolean isEmailAddressDuplicate(String emailAddress) {
        return contacts.stream().anyMatch(existingContact ->
                existingContact.getEmailAddress().equalsIgnoreCase(emailAddress)
        );
    }

    /**
     * Validates a contact's details.
     *
     * @param contact the contact to validate
     * @throws IllegalArgumentException if any required field is missing
     */
    private void validateContact(Contact contact) {
        if (contact.getName() == null || contact.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (contact.getPhoneNumber() == null || contact.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        if (contact.getEmailAddress() == null || contact.getEmailAddress().isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }
    }
}