package de.shokhor.costs.repository;

import de.shokhor.costs.model.CashAccountsAndCards;

import java.util.List;

public interface CashAccountsAndCardsRepository {

    CashAccountsAndCards save(CashAccountsAndCards cashAccountsAndCards, int userId);

    boolean delete (int id, int userId);

    CashAccountsAndCards get(int id, int userId);

    List<CashAccountsAndCards> getAll (int userId);
}
