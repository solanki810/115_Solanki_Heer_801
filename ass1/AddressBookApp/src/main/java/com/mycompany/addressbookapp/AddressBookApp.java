/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.addressbookapp;

/**
 *
 * @author root
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddressBookApp {
    private JFrame frame;
    private AddressBookManager manager;
    private JTextArea textArea;
    private JTextField nameField, homeAddressField, homePhoneField, businessAddressField,
                       businessPhoneField, faxField, cellularPhoneField, pagerField;

    public AddressBookApp() {
        frame = new JFrame("Address Book");
        manager = new AddressBookManager();
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        // Form fields
        nameField = new JTextField(20);
        homeAddressField = new JTextField(20);
        homePhoneField = new JTextField(20);
        businessAddressField = new JTextField(20);
        businessPhoneField = new JTextField(20);
        faxField = new JTextField(20);
        cellularPhoneField = new JTextField(20);
        pagerField = new JTextField(20);

        // Panel for form inputs
        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Home Address:"));
        panel.add(homeAddressField);
        panel.add(new JLabel("Home Phone:"));
        panel.add(homePhoneField);
        panel.add(new JLabel("Business Address:"));
        panel.add(businessAddressField);
        panel.add(new JLabel("Business Phone:"));
        panel.add(businessPhoneField);
        panel.add(new JLabel("Fax:"));
        panel.add(faxField);
        panel.add(new JLabel("Cellular Phone:"));
        panel.add(cellularPhoneField);
        panel.add(new JLabel("Pager:"));
        panel.add(pagerField);

        // Buttons
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton findButton = new JButton("Find");

        // Action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddressBook address = new AddressBook(
                        nameField.getText(), homeAddressField.getText(), homePhoneField.getText(),
                        businessAddressField.getText(), businessPhoneField.getText(), faxField.getText(),
                        cellularPhoneField.getText(), pagerField.getText()
                );
                manager.addAddress(address);
                displayAllAddresses();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                AddressBook existingAddress = manager.findAddressByName(name);
                if (existingAddress != null) {
                    existingAddress.setHomeAddress(homeAddressField.getText());
                    existingAddress.setHomePhone(homePhoneField.getText());
                    existingAddress.setBusinessAddress(businessAddressField.getText());
                    existingAddress.setBusinessPhone(businessPhoneField.getText());
                    existingAddress.setFax(faxField.getText());
                    existingAddress.setCellularPhone(cellularPhoneField.getText());
                    existingAddress.setPager(pagerField.getText());
                    displayAllAddresses();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                AddressBook address = manager.findAddressByName(name);
                if (address != null) {
                    manager.deleteAddress(manager.getAllAddresses().indexOf(address));
                    displayAllAddresses();
                }
            }
        });

        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                AddressBook address = manager.findAddressByName(name);
                if (address != null) {
                    textArea.setText(address.toString());
                } else {
                    textArea.setText("Address not found.");
                }
            }
        });

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(findButton);

        // Layout
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(textArea), BorderLayout.SOUTH);

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Method to display all address records
    private void displayAllAddresses() {
        textArea.setText("");
        for (AddressBook address : manager.getAllAddresses()) {
            textArea.append(address.toString() + "\n\n");
        }
    }

    public static void main(String[] args) {
        new AddressBookApp();
    }
}
