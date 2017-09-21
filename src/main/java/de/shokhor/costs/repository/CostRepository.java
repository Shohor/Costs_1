package de.shokhor.costs.repository;

import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.to.CostTo;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
public interface CostRepository
{
    Cost save (CostTo costTo, int userId);

    boolean delete (int costId, int userId);

    Cost get (int costId, int userId);

    List<Cost> getAll (int userId);

    Cost save(Cost cost, int userId, int groupId);

    Cost save(Cost cost, int userId, int groupId, int cashAccountsAndCardsId);

    List<Cost> getAllByGroup (int userId, int groupId);

    List<Cost> getFilteredList (int userId, Integer groupId, LocalDate startDate, LocalDate endDate);

    List<Cost> getBetween (int userId, LocalDate startDate, LocalDate endDate);

    LocalDate minDate(int userId);

    LocalDate maxDate(int userId);

    List<Cost> getBetweenByType(int userId, Integer typeCostId, LocalDate startDate, LocalDate endDate);

    List<Cost> getBetweenByCards(int userId, Integer cashAccountsAndCardsId, LocalDate startDate, LocalDate endDate);

    List<Cost> getBetweenByTypeAndCards(int userId, Integer cashAccountsAndCardsId, Integer typeCostId, LocalDate startDate, LocalDate endDate);
}
