package com.example.userservice.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PAYMENT")
public class PaymentAccount extends BankAccount {
    private double transactionLimit;

    // Default constructor
    public PaymentAccount() {
    }

    // Constructor with account number, balance, and transaction limit
    public PaymentAccount(String accountNumber, double balance, double transactionLimit) {
        super(accountNumber, balance);
        this.transactionLimit = transactionLimit;
    }

    // Getter and setter for transaction limit
    public double getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(double transactionLimit) {
        this.transactionLimit = transactionLimit;
    }
}
