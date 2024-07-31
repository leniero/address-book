import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        // Add example contacts
        addressBook.addContact(new Contact("John Doe", "1234567890", "john.doe@example.com"));
        addressBook.addContact(new Contact("Alice Doe", "0987654321", "alice.doe@example.com"));
        addressBook.addContact(new Contact("Peter Doe", "0987612345", "peter.doe@example.com"));
        addressBook.addContact(new Contact("Lana Doe", "6789054321", "lana.doe@example.com"));

        while (true) {
            System.out.println("Address Book Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact by Name");
            System.out.println("3. Search Contact by Phone Number");
            System.out.println("4. Search Contact by Email Address");
            System.out.println("5. View All Contacts");
            System.out.println("6. Edit Contact");
            System.out.println("7. Remove Contact");
            System.out.println("8. Delete All Contacts");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();
                    try {
                        addressBook.addContact(new Contact(name, phoneNumber, emailAddress));
                        System.out.println("Contact added successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    addressBook.searchContactByName(searchName).forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter phone number to search: ");
                    String searchPhoneNumber = scanner.nextLine();
                    addressBook.searchContactByPhoneNumber(searchPhoneNumber).forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter email address to search: ");
                    String searchEmailAddress = scanner.nextLine();
                    addressBook.searchContactByEmailAddress(searchEmailAddress).forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("All Contacts:");
                    addressBook.getAllContacts().forEach(contact -> {
                        System.out.println(contact);
                        System.out.println("---------------");
                    });
                    break;
                case 6:
                    System.out.print("Enter name of the contact to edit: ");
                    String editName = scanner.nextLine();
                    List<Contact> contacts = addressBook.searchContactByName(editName);
                    if (contacts.isEmpty()) {
                        System.out.println("No contacts found with that name.");
                    } else {
                        for (int i = 0; i < contacts.size(); i++) {
                            System.out.println((i + 1) + ". " + contacts.get(i));
                        }
                        System.out.print("Select the contact to edit by number: ");
                        int contactIndex = scanner.nextInt() - 1;
                        scanner.nextLine(); // Consume newline
                        if (contactIndex >= 0 && contactIndex < contacts.size()) {
                            Contact contactToEdit = contacts.get(contactIndex);
                            System.out.print("Enter new name (leave blank to keep current): ");
                            String newName = scanner.nextLine();
                            System.out.print("Enter new phone number (leave blank to keep current): ");
                            String newPhoneNumber = scanner.nextLine();
                            System.out.print("Enter new email address (leave blank to keep current): ");
                            String newEmailAddress = scanner.nextLine();

                            if (!newName.isEmpty()) {
                                contactToEdit.setName(newName);
                            }
                            if (!newPhoneNumber.isEmpty()) {
                                contactToEdit.setPhoneNumber(newPhoneNumber);
                            }
                            if (!newEmailAddress.isEmpty()) {
                                contactToEdit.setEmailAddress(newEmailAddress);
                            }
                            System.out.println("Contact updated successfully.");
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    }
                    break;
                case 7:
                    System.out.print("Enter name of the contact to remove: ");
                    String removeName = scanner.nextLine();
                    List<Contact> contactsToRemove = addressBook.searchContactByName(removeName);
                    if (contactsToRemove.isEmpty()) {
                        System.out.println("No contacts found with that name.");
                    } else {
                        for (int i = 0; i < contactsToRemove.size(); i++) {
                            System.out.println((i + 1) + ". " + contactsToRemove.get(i));
                        }
                        System.out.print("Select the contact to remove by number: ");
                        int contactIndex = scanner.nextInt() - 1;
                        scanner.nextLine(); // Consume newline
                        if (contactIndex >= 0 && contactIndex < contactsToRemove.size()) {
                            Contact contactToRemove = contactsToRemove.get(contactIndex);
                            if (addressBook.removeContact(contactToRemove.getName())) {
                                System.out.println("Contact removed successfully.");
                            } else {
                                System.out.println("Error removing contact.");
                            }
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    }
                    break;
                case 8:
                    System.out.print("Are you sure you want to delete all contacts? (yes/no): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                        addressBook.deleteAllContacts(true);
                        System.out.println("All contacts deleted.");
                    } else {
                        System.out.println("Operation cancelled.");
                    }
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}