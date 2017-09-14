package de.shokhor.costs.service;

import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.to.IncomeTo;

import java.util.List;

public interface IncomeService {

    Income save(Income income, int userId);

    Income update(Income income, int userId);

    void delete(int id, int userId);

    Income get(int id, int userId);

    List<Income> getAll(int userId);

    Income save(IncomeTo incomeTo, int userId);

    Income update (IncomeTo incomeTo, int userId);
}
