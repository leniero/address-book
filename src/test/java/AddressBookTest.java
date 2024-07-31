import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    private AddressBook addressBook;
    private Contact contact1;
    private Contact contact2;

    @BeforeEach
    void setUp() {
        addressBook = new AddressBook();
        contact1 = new Contact("John Doe", "1234567890", "john@example.com");
        contact2 = new Contact("Jane Doe", "0987654321", "jane@example.com");
    }

    @Test
    void testAddContact() {
        addressBook.addContact(contact1);
        List<Contact> contacts = addressBook.getAllContacts();
        assertEquals(1, contacts.size());
        assertEquals(contact1, contacts.get(0));
    }

    @Test
    void testAddDuplicateContact() {
        addressBook.addContact(contact1);
        Contact duplicateContact = new Contact("John Smith", "1234567890", "john.smith@example.com");
        assertThrows(IllegalArgumentException.class, () -> addressBook.addContact(duplicateContact));
    }

    @Test
    void testSearchContactByName() {
        addressBook.addContact(contact1);
        addressBook.addContact(contact2);
        List<Contact> result = addressBook.searchContactByName("John");
        assertEquals(1, result.size());
        assertEquals(contact1, result.get(0));
    }

    @Test
    void testSearchContactByPhoneNumber() {
        addressBook.addContact(contact1);
        addressBook.addContact(contact2);
        List<Contact> result = addressBook.searchContactByPhoneNumber("1234567890");
        assertEquals(1, result.size());
        assertEquals(contact1, result.get(0));
    }

    @Test
    void testSearchContactByEmailAddress() {
        addressBook.addContact(contact1);
        addressBook.addContact(contact2);
        List<Contact> result = addressBook.searchContactByEmailAddress("john@example.com");
        assertEquals(1, result.size());
        assertEquals(contact1, result.get(0));
    }

    @Test
    void testSearchContactsSorted() {
        Contact contact3 = new Contact("Alice Doe", "1122334455", "alice@example.com");
        addressBook.addContact(contact1);
        addressBook.addContact(contact2);
        addressBook.addContact(contact3);
        List<Contact> result = addressBook.searchContactsSorted("Doe");
        assertEquals(3, result.size());
        assertEquals(contact3, result.get(0));
        assertEquals(contact2, result.get(1));
        assertEquals(contact1, result.get(2));
    }

    @Test
    void testDeleteAllContacts() {
        addressBook.addContact(contact1);
        addressBook.addContact(contact2);
        addressBook.deleteAllContacts(true);
        List<Contact> contacts = addressBook.getAllContacts();
        assertEquals(0, contacts.size());
    }

    @Test
    void testRemoveContact() {
        addressBook.addContact(contact1);
        assertTrue(addressBook.removeContact(contact1.getName()));
        assertFalse(addressBook.removeContact(contact1.getName()));
    }

    @Test
    void testEditContact() {
        addressBook.addContact(contact1);
        assertTrue(addressBook.editContact("1234567890", "John Doe Updated", "john.updated@example.com"));
        Contact updatedContact = addressBook.searchContactByPhoneNumber("1234567890").get(0);
        assertEquals("John Doe Updated", updatedContact.getName());
        assertEquals("john.updated@example.com", updatedContact.getEmailAddress());
    }

    @Test
    void testAddContactWithMissingName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact invalidContact = new Contact(null, "1234567890", "missing@example.com");
            addressBook.addContact(invalidContact);
        });
    }

    @Test
    void testAddContactWithMissingPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact invalidContact = new Contact("Missing Phone", null, "missing@example.com");
            addressBook.addContact(invalidContact);
        });
    }

    @Test
    void testAddContactWithMissingEmailAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            Contact invalidContact = new Contact("Missing Email", "1234567890", null);
            addressBook.addContact(invalidContact);
        });
    }

    @Test
    void testIsDuplicate() {
        addressBook.addContact(contact1);
        Contact duplicateContact = new Contact("John Smith", "1234567890", "john.smith@example.com");
        assertTrue(addressBook.isDuplicate(duplicateContact));
        assertFalse(addressBook.isDuplicate(contact2));
    }

    @Test
    void testIsDuplicatePhoneNumber() {
        addressBook.addContact(contact1);
        Contact duplicatePhoneContact = new Contact("Jane Smith", "1234567890", "jane.smith@example.com");
        assertTrue(addressBook.isDuplicate(duplicatePhoneContact));
    }

    @Test
    void testIsDuplicateEmail() {
        addressBook.addContact(contact1);
        Contact duplicateEmailContact = new Contact("Jane Smith", "0987654321", "john@example.com");
        assertTrue(addressBook.isDuplicate(duplicateEmailContact));
    }

    @Test
    void testViewAllContacts() {
        addressBook.addContact(contact1);
        addressBook.addContact(contact2);
        List<Contact> contacts = addressBook.getAllContacts();
        assertEquals(2, contacts.size());
        assertEquals(contact2, contacts.get(0));
        assertEquals(contact1, contacts.get(1));
    }



}