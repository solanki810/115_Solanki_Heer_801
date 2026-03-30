/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.addressbookapp;

/**
 *
 * @author root
 */
public class AddressBook {
    private String name;
    private String homeAddress;
    private String homePhone;
    private String businessAddress;
    private String businessPhone;
    private String fax;
    private String cellularPhone;
    private String pager;

    // Constructor
    public AddressBook(String name, String homeAddress, String homePhone, 
                       String businessAddress, String businessPhone, 
                       String fax, String cellularPhone, String pager) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.homePhone = homePhone;
        this.businessAddress = businessAddress;
        this.businessPhone = businessPhone;
        this.fax = fax;
        this.cellularPhone = cellularPhone;
        this.pager = pager;
    }

    // Getters and setters for each field
    public String getName() { return name; }
    public String getHomeAddress() { return homeAddress; }
    public String getHomePhone() { return homePhone; }
    public String getBusinessAddress() { return businessAddress; }
    public String getBusinessPhone() { return businessPhone; }
    public String getFax() { return fax; }
    public String getCellularPhone() { return cellularPhone; }
    public String getPager() { return pager; }

    public void setName(String name) { this.name = name; }
    public void setHomeAddress(String homeAddress) { this.homeAddress = homeAddress; }
    public void setHomePhone(String homePhone) { this.homePhone = homePhone; }
    public void setBusinessAddress(String businessAddress) { this.businessAddress = businessAddress; }
    public void setBusinessPhone(String businessPhone) { this.businessPhone = businessPhone; }
    public void setFax(String fax) { this.fax = fax; }
    public void setCellularPhone(String cellularPhone) { this.cellularPhone = cellularPhone; }
    public void setPager(String pager) { this.pager = pager; }

    @Override
    public String toString() {
        return "Name: " + name + "\nHome Address: " + homeAddress + "\nHome Phone: " + homePhone +
               "\nBusiness Address: " + businessAddress + "\nBusiness Phone: " + businessPhone +
               "\nFax: " + fax + "\nCellular Phone: " + cellularPhone + "\nPager: " + pager;
    }
}
