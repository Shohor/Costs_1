package de.shokhor.costs.repository;

import de.shokhor.costs.model.Income.TypeIncome;

import java.util.List;

public interface TypeIncomeRepository {

    TypeIncome save(TypeIncome typeIncome, int userId);

    boolean delete(int id, int userId);

    TypeIncome get(int id, int userId);

    List<TypeIncome> getAll(int userId);
}
