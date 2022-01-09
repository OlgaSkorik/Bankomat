package Cards;

import enumBank.Bank;

import java.math.BigDecimal;

public final class CreditCard extends Card {
    public String creditLimit = "2000.00";
    public CreditCard(Bank bankCard, String nameOwner, String numCard, String pin, BigDecimal balance) {
        super(bankCard, nameOwner, numCard, pin, balance);
    }
}