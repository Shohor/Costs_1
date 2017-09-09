package de.shokhor.costs.service;

import de.shokhor.costs.model.Income.Income;

import java.util.List;

public interface IncomeService {

    Income save(Income income, int cashAccountsAndCards_id, int typeIncome_id, int userId);

    Income update(Income income, int cashAccountsAndCards_id, int typeIncome_id, int userId);

    void delete(int id, int userId);

    Income get(int id, int userId);

    List<Income> getAll(int userId);
}
