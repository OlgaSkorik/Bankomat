package Bankomat;

import Cards.*;
import enumBank.Bank;

import java.math.BigDecimal;
import java.util.Scanner;

public final class ATM {
    private Bank bankAtm;
    public boolean correct = true;

    public ATM(Bank bankAtm) {
        this.bankAtm = bankAtm;
    }

    public void enterPin(Card card) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your pin, please.");
        correct = (sc.next().equals(card.getPin()));
    }

    public void checkPinAndOperations(Card card) {
        if (correct) {
            operations(card);
        } else {
            System.out.println("You entered an incorrect pin.");
        }
    }

    public void operations(Card card) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, select an operation: 1 - balance check, 2 - cash withdrawal.");
        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                checkBalance(card);
                break;
            case 2:
                cashWithdrawal((CreditCard) card);
                break;
            default:
                System.out.println("You can choose operation 1 or 2.");
        }
    }

    public void checkBalance(Card card) {
        System.out.println("Balance check.");
        System.out.println("Your balance is " + card.getBalance() + " rubles.");
        chooseDifOper(card);
    }

    private void chooseDifOper(Card card) {
        System.out.println("Do you want to choose a different operation? (yes/no)");
        Scanner sc = new Scanner(System.in);
        if (sc.next().equals("yes")) {
            enterPin(card);
            checkPinAndOperations(card);
        } else {
            System.out.println();
        }
    }

    public void cashWithdrawal(CreditCard card) {
        if (card.getBankCard() == bankAtm) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Withdraw cash.");
            System.out.println("The following bills are available: 5, 10, 20, 50.");
            System.out.print("Please, enter the required amount: ");
            int scCash = sc.nextInt();
            if (scCash % 5 == 0) {
                String cash = String.valueOf(scCash);
                BigDecimal theCash = new BigDecimal(cash);
                if (card.getBalance().compareTo(theCash) >= 0) {
                    System.out.println("Please, take money.");
                    System.out.println("Your balance is " + card.getBalance().subtract(theCash) + " rubles.");
                    card.setBalance(card.getBalance().subtract(theCash));
                } else if (card.getBalance().compareTo(theCash) < 0) {
                    System.out.println("Withdrawal amount exceeds the balance.");
                    BigDecimal credLim = new BigDecimal(card.creditLimit);
                    if (theCash.subtract(card.getBalance()).compareTo(credLim) <= 0) {
                        System.out.println("Are you ready to take a loan? (yes/no)");
                        if (sc.next().equals("yes")) {
                            System.out.println("Congratulations, you have taken out a loan! Don't forget to repay it on time!");
                            System.out.println("Your balance is " + card.getBalance().subtract(theCash) + " rubles.");
                            card.setBalance(card.getBalance().subtract(theCash));
                        }
                    }
                }
            } else {
                System.out.println("Banknotes of this denomination are not issued.");
            }
        } else {
            System.out.println("Sorry, it is not possible to withdraw cash from this bank's card.");
        }
        chooseDifOper(card);
    }
}