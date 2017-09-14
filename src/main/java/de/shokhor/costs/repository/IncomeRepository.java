package de.shokhor.costs.repository;

import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.to.IncomeTo;

import java.util.List;

public interface IncomeRepository {

    Income save(Income income, int userId);

    boolean delete(int id, int userId);

    Income get(int id, int userId);

    List<Income> getAll(int userId);

    Income save(IncomeTo incomeTo, int userId);
}
