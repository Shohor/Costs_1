package de.shokhor.costs.repository;

import de.shokhor.costs.model.Income.Income;

import java.util.List;

public interface IncomeRepository {

    Income save(Income income, int cashAccountsAndCards_id, int typeIncome_id, int userId);

    boolean delete(int id, int userId);

    Income get(int id, int userId);

    List<Income> getAll(int userId);
}
