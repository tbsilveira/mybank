package com.mybank.domain.account;

import com.mybank.ConnectionFactory;
import com.mybank.domain.BusinessRuleException;

import java.sql.Connection;

public class AccountService {

    private ConnectionFactory connectionFactory;

    public AccountService() {
        this.connectionFactory = new ConnectionFactory();
    }

    public void createAccount(AccountDataRegister accountData){
        Connection conn = connectionFactory.getConnection();
        new AccountDAO(conn).create(accountData);
    }


    public Account getAccountByNumber(Integer number) {
        Connection conn = connectionFactory.getConnection();
        Account account = new AccountDAO(conn).getAccountByNumber(number);
        if (account != null) {
            return account;
        } else {
            throw new BusinessRuleException("Error: Account number not found!");
        }
    }
}
