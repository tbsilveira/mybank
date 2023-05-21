package com.mybank.domain.account;

import com.mybank.domain.client.Client;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {

    private Connection conn;

    AccountDAO (Connection connection) {
        this.conn = connection;
    }

    public void create(AccountDataRegister dataRegister) {
        var client = new Client(dataRegister.clientData());
        var account = new Account(dataRegister.number(), client, BigDecimal.ZERO);

        String sql = "INSERT INTO accounts (account_number, name, tax_number, email, balance) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, account.getNumber());
            ps.setString(2, dataRegister.clientData().name());
            ps.setString(3, dataRegister.clientData().taxNumber());
            ps.setString(4, dataRegister.clientData().email());
            ps.setBigDecimal(5, BigDecimal.ZERO);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
