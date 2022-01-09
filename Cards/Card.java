package Cards;

import enumBank.Bank;

import java.math.BigDecimal;

public abstract class Card {
    private Bank bankCard;
    private String nameOwner;
    private String numCard;
    private String pin;
    private BigDecimal balance;

    public Card(Bank bankCard, String nameOwner, String numCard, String pin, BigDecimal balance) {
        this.nameOwner = nameOwner;
        this.numCard = numCard;
        this.pin = pin;
        this.balance = balance;
        this.bankCard = bankCard;
    }

    public String getNumCard() {
        return numCard;
    }

    public String getPin() {
        return pin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Bank getBankCard() {
        return bankCard;
    }

}
