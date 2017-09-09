package de.shokhor.costs.service;

import de.shokhor.costs.model.Income.TypeIncome;

import java.util.List;

public interface TypeIncomeService {

    TypeIncome save(TypeIncome typeIncome, int userId);

    void delete(int id, int userId);

    TypeIncome get(int id, int userId);

    List<TypeIncome> getAll(int userId);
}
