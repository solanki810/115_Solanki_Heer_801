/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.addressbookapp;

/**
 *
 * @author root
 */
import java.util.ArrayList;

public class AddressBookManager {
    private ArrayList<AddressBook> addressList;

    // Constructor
    public AddressBookManager() {
        addressList = new ArrayList<>();
    }

    // Add a new address record
    public void addAddress(AddressBook address) {
        addressList.add(address);
    }

    // Edit an existing address record
    public void editAddress(int index, AddressBook address) {
        if (index >= 0 && index < addressList.size()) {
            addressList.set(index, address);
        }
    }

    // Delete an address record
    public void deleteAddress(int index) {
        if (index >= 0 && index < addressList.size()) {
            addressList.remove(index);
        }
    }

    // Find an address record by name
    public AddressBook findAddressByName(String name) {
        for (AddressBook address : addressList) {
            if (address.getName().equalsIgnoreCase(name)) {
                return address;
            }
        }
        return null;
    }

    // Get all address records
    public ArrayList<AddressBook> getAllAddresses() {
        return addressList;
    }
}
