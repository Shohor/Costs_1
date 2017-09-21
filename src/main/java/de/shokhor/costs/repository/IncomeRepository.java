package de.shokhor.costs.repository;

import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.to.IncomeTo;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository {

    Income save(Income income, int userId);

    boolean delete(int id, int userId);

    Income get(int id, int userId);

    List<Income> getAll(int userId);

    Income save(IncomeTo incomeTo, int userId);

    List<Income> getAllByGroup (int userId, int typeIncomeId);

    List<Income> getFilteredList (int userId, Integer typeIncomeId, LocalDate startDate, LocalDate endDate);

    List<Income> getBetween (int userId, LocalDate startDate, LocalDate endDate);

    LocalDate minDate(int userId);

    LocalDate maxDate(int userId);

    List<Income> getBetweenByType(int userId, Integer typeIncomeId, LocalDate startDate, LocalDate endDate);

    List<Income> getBetweenByTypeAndCards(int userId, Integer cashAccountsAndCardsId, Integer typeIncomeId, LocalDate startDate, LocalDate endDate);

    List<Income> getBetweenByCards(int userId, Integer cashAccountsAndCardsId, LocalDate startDate, LocalDate endDate);
}
