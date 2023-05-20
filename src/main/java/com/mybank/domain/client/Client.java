package com.mybank.domain.client;

public class Client {
    private String name;
    private String taxNumber;
    private String email;

    public Client (ClientDataRegister clientData) {
        this.name = clientData.name();
        this.taxNumber = clientData.taxNumber();
        this.email = clientData.email();
    }

    public String getName() {
        return name;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public String getEmail() {
        return email;
    }

}
