package de.shokhor.costs.service;

import de.shokhor.costs.model.CashAccountsAndCards;

import java.util.List;

public interface CashAccountsAndCardsService {

    CashAccountsAndCards save (CashAccountsAndCards cashAccountsAndCards, int userId);

    CashAccountsAndCards update (CashAccountsAndCards cashAccountsAndCards, int userId);

    void delete (int id, int userId);

    CashAccountsAndCards get(int id, int userId);

    List<CashAccountsAndCards> getAll (int userId);
}
