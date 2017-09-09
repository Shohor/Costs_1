package de.shokhor.costs;

import de.shokhor.costs.matcher.ModelMatcher;
import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.model.TypeCashAccountsAndCards;

import java.util.Arrays;
import java.util.List;

public class cashAccountsAndCardsTestData {

    public static final ModelMatcher<CashAccountsAndCards> MATCHER = ModelMatcher.of(CashAccountsAndCards.class);

    public static final int CASH_ACCOUNTS_AND_CARDS_ID = 1;

    public static final CashAccountsAndCards CASH_ACCOUNTS_AND_CARDS_1 = new CashAccountsAndCards(CASH_ACCOUNTS_AND_CARDS_ID,2000,"", TypeCashAccountsAndCards.Cash);
    public static final CashAccountsAndCards CASH_ACCOUNTS_AND_CARDS_2 = new CashAccountsAndCards(CASH_ACCOUNTS_AND_CARDS_ID+1,5000,"", TypeCashAccountsAndCards.Credit_Card);
    public static final CashAccountsAndCards CASH_ACCOUNTS_AND_CARDS_3 = new CashAccountsAndCards(CASH_ACCOUNTS_AND_CARDS_ID+2,2854,"", TypeCashAccountsAndCards.Debit_Card);

    public static final List<CashAccountsAndCards> CASH_ACCOUNTS_AND_CARDS = Arrays.asList(CASH_ACCOUNTS_AND_CARDS_1,CASH_ACCOUNTS_AND_CARDS_2);

    public static final CashAccountsAndCards getCreated()
    {
        return new CashAccountsAndCards(CASH_ACCOUNTS_AND_CARDS_ID+3, 3000, "New", TypeCashAccountsAndCards.PayPal);
    }

    public static final CashAccountsAndCards getUpdate()
    {
        return new CashAccountsAndCards(CASH_ACCOUNTS_AND_CARDS_ID, 2001, "aaaa", TypeCashAccountsAndCards.Cash);
    }
}
