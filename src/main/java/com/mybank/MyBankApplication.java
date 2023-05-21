package com.mybank;

import com.mybank.domain.BusinessRuleException;
import com.mybank.domain.account.AccountDataRegister;
import com.mybank.domain.account.AccountService;
import com.mybank.domain.client.ClientDataRegister;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MyBankApplication {

    private static AccountService service = new AccountService();
    private static Scanner input = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {

        AccountService service = new AccountService();
        var option = showMenu();

        while (option != 9) {
            try {
                switch (option) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        getAccountByNumber();
                        break;
                    case 9:
                        break;

                }
            } catch (BusinessRuleException e) {
                System.out.println(e.getMessage());
                System.out.println("\nPress ENTER to return to main menu");
                input.next();
            }

            option = showMenu();
        }
        System.out.println("Closing application...");

    }



    private static int showMenu() {
        System.out.println("""
                    \n*** MyBank Application ***
                    --------------------------
                    Choose one option:
                    1 - Create account
                    2 - List one account
                    3 - List all accounts
                    4 - Deposit
                    5 - Withdraw
                    6 - Transfer
                    7 - Show balance
                    8 - Close account
                    9 - Exit ->]
                    --------------------------
                    """);
        return input.nextInt();
    }

    private static void createAccount() {
        System.out.println("Inform the Account Number:");
        var number = input.nextInt();
        System.out.println("Inform the NAME of the account holder:");
        var name = input.next();
        System.out.println("Inform the TAX NUMBER of the account holder:");
        var taxNumber = input.next();
        System.out.println("Inform the EMAIL of the account holder:");
        var email = input.next();

        service.createAccount(new AccountDataRegister(number, new ClientDataRegister(name, taxNumber, email)));

        System.out.println("\n# Account created #");
        System.out.println("Account Number: " + number +
                "\nName: " + name +
                "\nTax Number: " + taxNumber +
                "\nEmail: " + email);
        System.out.println("\nPress ENTER to return to main menu");
        input.next();

    }

    private static void getAccountByNumber() {

        System.out.println("""
                *** List one specific account ***
                -> Inform the account number:""");
        var number = input.nextInt();
        var account = service.getAccountByNumber(number);

        System.out.println(
                "\nAccount Number: " + account.getNumber() +
                "\nName: " + account.getAccountHolder().getName() +
                "\nTax Number: " + account.getAccountHolder().getTaxNumber() +
                "\nE-mail: " + account.getAccountHolder().getEmail() +
                "\nBalance: " + account.getBalance());

        System.out.println("\nPress ENTER to return to main menu");
        input.next();
    }

}