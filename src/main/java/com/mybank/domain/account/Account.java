package com.mybank.domain.account;

import com.mybank.domain.client.Client;

import java.math.BigDecimal;

public class Account {
    private Integer number;
    private BigDecimal balance;
    private Client accountHolder;

    public Account(Integer number, BigDecimal balance, Client accountHolder){
        this.number = number;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    public boolean hasFund() {
        return this.balance.compareTo(BigDecimal.ZERO) != 0;
    }

}
