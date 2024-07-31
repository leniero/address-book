import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    public void testGetName() {
        Contact contact = new Contact("John Doe", "1234567890", "john.doe@example.com");
        assertEquals("John Doe", contact.getName());
    }

    @Test
    public void testGetPhoneNumber() {
        Contact contact = new Contact("John Doe", "1234567890", "john.doe@example.com");
        assertEquals("1234567890", contact.getPhoneNumber());
    }

    @Test
    public void testGetEmailAddress() {
        Contact contact = new Contact("John Doe", "1234567890", "john.doe@example.com");
        assertEquals("john.doe@example.com", contact.getEmailAddress());
    }

    @Test
    public void testSetName() {
        Contact contact = new Contact("John Doe", "1234567890", "john.doe@example.com");
        contact.setName("Jane Doe");
        assertEquals("Jane Doe", contact.getName());
    }

    @Test
    public void testSetPhoneNumber() {
        Contact contact = new Contact("John Doe", "1234567890", "john.doe@example.com");
        contact.setPhoneNumber("0987654321");
        assertEquals("0987654321", contact.getPhoneNumber());
    }

    @Test
    public void testSetEmailAddress() {
        Contact contact = new Contact("John Doe", "1234567890", "john.doe@example.com");
        contact.setEmailAddress("jane.doe@example.com");
        assertEquals("jane.doe@example.com", contact.getEmailAddress());
    }

    @Test
    public void testCreateContactWithMissingName() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "1234567890", "john.doe@example.com"));
    }

    @Test
    public void testCreateContactWithMissingPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("John Doe", null, "john.doe@example.com"));
    }

    @Test
    public void testCreateContactWithMissingEmailAddress() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("John Doe", "1234567890", null));
    }
}