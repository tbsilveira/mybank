package com.mybank.domain.account;

import com.mybank.ConnectionFactory;

import java.sql.Connection;

public class AccountService {

    private ConnectionFactory connectionFactory;

    public AccountService() {
        this.connectionFactory = new ConnectionFactory();
    }

    public void createAccount(AccountDataRegister dataRegister){
        Connection conn = connectionFactory.getConnection();
        new AccountDAO(conn).create(dataRegister);
    }

}
