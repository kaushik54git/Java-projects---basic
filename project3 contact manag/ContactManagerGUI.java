import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class ContactManager {
    private ArrayList<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public void addContact(String name, String phoneNumber, String email) {
        contacts.add(new Contact(name, phoneNumber, email));
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void editContact(int index, String newPhoneNumber, String newEmail) {
        Contact contact = contacts.get(index);
        contact.setPhoneNumber(newPhoneNumber);
        contact.setEmail(newEmail);
    }

    public void deleteContact(int index) {
        contacts.remove(index);
    }
}

public class ContactManagerGUI extends JFrame {
    private ContactManager contactManager;

    private JList<String> contactList;

    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField emailField;

    public ContactManagerGUI() {
        contactManager = new ContactManager();

        setTitle("Contact Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        inputPanel.add(phoneNumberField);
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                contactManager.addContact(name, phoneNumber, email);
                updateContactList();
            }
        });
        inputPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Contact");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex != -1) {
                    contactManager.deleteContact(selectedIndex);
                    updateContactList();
                }
            }
        });
        inputPanel.add(deleteButton);

        contactList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(contactList);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void updateContactList() {
        ArrayList<Contact> contacts = contactManager.getContacts();
        String[] contactNames = new String[contacts.size()];
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            contactNames[i] = contact.getName();
        }
        contactList.setListData(contactNames);
    }

    public static void main(String[] args) {
        new ContactManagerGUI();
    }
}
