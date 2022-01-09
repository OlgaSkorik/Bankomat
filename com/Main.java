package com.company;

import Cards.*;
import Bankomat.ATM;
import enumBank.Bank;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM(Bank.PRIVAT_BANK);
        Card card = new CreditCard(Bank.PRIVAT_BANK,"VOLHA SKORIK","card","1010",new BigDecimal(1550.50).setScale(2,BigDecimal.ROUND_HALF_UP));
        System.out.println("Insert your card, please.");//enter "card"
        Scanner sc = new Scanner(System.in);

        if (sc.next().equals(card.getNumCard())) {
            atm.enterPin(card);
            atm.checkPinAndOperations(card);
            if (!atm.correct) {
                for (int i = 1; i < 4; i++) {
                    if (i < 3) {
                        atm.enterPin(card);
                        atm.checkPinAndOperations(card);
                    }
                    if (i == 3) {
                        System.out.println("Sorry, but your card is blocked.");
                    }
                }
            }
            System.out.println("Please, take your card.");
        }
    }
}
