package de.shokhor.costs.repository;

import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.model.TypeCashAccountsAndCards;

import java.util.List;
import java.util.Set;

public interface CashAccountsAndCardsRepository {

    CashAccountsAndCards save(CashAccountsAndCards cashAccountsAndCards, int userId);

    boolean delete (int id, int userId);

    CashAccountsAndCards get(int id, int userId);

    List<CashAccountsAndCards> getAll (int userId);

    Double summIncome(int id, int userId);

    Double summCost(int id, int userId);
}
