package com.mybank.domain.account;

import com.mybank.ConnectionFactory;
import com.mybank.domain.BusinessRuleException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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

    private Set<Account> accounts = new HashSet<>();

    public Set<Account> getAllAccounts() {
        Connection conn = connectionFactory.getConnection();
        return new AccountDAO(conn).getAllAccounts();
    }

    public void deposit(Integer number, BigDecimal value){
        var account = getAccountByNumber(number);
        if(value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessRuleException("The amount need to be more than EUR 0,00");
        }
        BigDecimal newBalance = account.getBalance().add(value);
        changeBalance(account, newBalance);
    }

    public void withdraw(Integer numberAccount, BigDecimal withdrawValue) {
        var account = getAccountByNumber(numberAccount);
        if(account.getBalance().compareTo(withdrawValue) < 0){
            throw new BusinessRuleException("There is no balance available for this withdraw");
        }
        BigDecimal newBalance = account.getBalance().subtract(withdrawValue);
        changeBalance(account, newBalance);
    }

    public void transfer(Integer senderAccount, Integer recipientAccount, BigDecimal transferValue) {
        this.withdraw(senderAccount, transferValue);
        this.deposit(recipientAccount, transferValue);
    }

    private void changeBalance(Account account, BigDecimal value) {
        Connection conn = connectionFactory.getConnection();
        new AccountDAO(conn).changeBalance(account.getNumber(), value);
    }

}
