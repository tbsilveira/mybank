package com.mybank;

import com.mybank.domain.account.AccountService;

import java.util.Random;
import java.util.Scanner;

public class MyBankApplication {

    private static Scanner input = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {

        AccountService service = new AccountService();
        var option = showMenu();

        while (option != 9) {
            switch (option) {
                case 1:
                    createAccount();
                    break;
                case 9:
                    break;

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

    private static int createNumber() {
        Random number = new Random()
    }

    private static void createAccount() {
        System.out.println("Inform the account number:");
        var number = input.nextInt();
        System.out.println("Inform the NAME of the account holder:");
        var name = input.next();
        System.out.println("Inform the TAX NUMBER of the account holder:");
        var taxNumber = input.nextInt();
        System.out.println("Inform the EMAIL of the account holder:");
        var email = input.next();

        System.out.println("# Account created #");
        System.out.println("Account Number: " + number +
                "\nName: " + name +
                "\nTax Number: " + taxNumber +
                "\nEmail: " + email);
        System.out.println("\nPress ENTER to return to main menu");
        input.next();

    }
}