package com.mybank.domain.account;

import com.mybank.domain.client.Client;

import java.math.BigDecimal;

public class Account {
    private Integer number;
    private Client accountHolder;
    private BigDecimal balance;


    public Account(Integer number, Client accountHolder, BigDecimal balance) {
        this.number = number;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }


    public boolean hasFund() {
        return this.balance.compareTo(BigDecimal.ZERO) != 0;
    }

    public Integer getNumber() {
        return number;
    }

    public Client getAccountHolder() {
        return accountHolder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account: ( " +
                "Number: " + number +
                ", Name: " + accountHolder.getName() +
                ", Balance: EUR " + balance + " )";
    }
}
