/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author Aske
 */
public class CustomerDTO {
    
    BankCustomer bank = new BankCustomer();
    
    private long customerID;
    private String fullName;
    private String accountNumber;
    private double balance;

    public CustomerDTO() {
    }

    public CustomerDTO(BankCustomer b) {
        this.customerID = b.getId();
        this.fullName = b.getFirstName() + " " + b.getLastName();
        this.accountNumber = b.getAccountNumber();
        this.balance = b.getBalance();
    }

    public BankCustomer getBank() {
        return bank;
    }

    public void setBank(BankCustomer bank) {
        this.bank = bank;
    }

    public long getCustomerID() {
        return customerID;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
    
}
