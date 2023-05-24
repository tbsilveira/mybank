package com.mybank.domain.account;

import com.mybank.domain.client.Client;
import com.mybank.domain.client.ClientDataRegister;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AccountDAO {

    private Connection conn;

    AccountDAO(Connection connection) {
        this.conn = connection;
    }

    public void create(AccountDataRegister accountData) {
        var client = new Client(accountData.clientData());
        var account = new Account(accountData.number(), client, BigDecimal.ZERO, true);

        String sql = "INSERT INTO accounts (account_number, name, tax_number, email, balance, is_active) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, account.getNumber());
            ps.setString(2, accountData.clientData().name());
            ps.setString(3, accountData.clientData().taxNumber());
            ps.setString(4, accountData.clientData().email());
            ps.setBigDecimal(5, BigDecimal.ZERO);
            ps.setBoolean(6, true);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        connTest();
    }

    public Account getAccountByNumber(int number) {
        PreparedStatement ps;
        ResultSet rs;
        Account account = null;
        String sql = "SELECT * FROM accounts WHERE account_number = ? and is_active = true";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, number);
            rs = ps.executeQuery();

            while (rs.next()) {
                Integer numberAccount = rs.getInt(1);
                String name = rs.getString(2);
                String taxNumber = rs.getString(3);
                String email = rs.getString(4);
                BigDecimal balance = rs.getBigDecimal(5);
                Boolean isActive = rs.getBoolean(6);

                ClientDataRegister clientData = new ClientDataRegister(name, taxNumber, email);
                Client client = new Client(clientData);
                account = new Account(numberAccount, client, balance, isActive );
            }
            ps.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        connTest();
        return account;
    }

    public Set<Account> getAllAccounts() {
        PreparedStatement ps;
        ResultSet rs;
        Set<Account> accounts = new HashSet<>();
        String sql = "SELECT * FROM accounts WHERE is_active = true";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Integer numberAccount = rs.getInt(1);
                String name = rs.getString(2);
                String taxNumber = rs.getString(3);
                String email = rs.getString(4);
                BigDecimal balance = rs.getBigDecimal(5);
                Boolean isActive = rs.getBoolean(6);

                ClientDataRegister clientData = new ClientDataRegister(name, taxNumber, email);
                Client client = new Client(clientData);

                accounts.add(new Account(numberAccount, client, balance, isActive));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        connTest();
        return accounts;
    }

    public void changeBalance(Integer number, BigDecimal value) {
        PreparedStatement ps;
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, value);
            ps.setInt(2, number);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        connTest();
    }

    public void delete(Integer number) {
        PreparedStatement ps;
        String sql = "UPDATE accounts SET is_active = false WHERE account_number = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, number);
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void connTest(){
        try {
            if(conn.isClosed()){
                System.out.println("CONN IS CLOSED");
            } else {
                System.out.println("CONN IS OPEN");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


