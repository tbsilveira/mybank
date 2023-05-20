package com.mybank.domain.account;

import com.mybank.domain.client.ClientDataRegister;

public record AccountDataRegister(Integer number, ClientDataRegister clientData) {
}
