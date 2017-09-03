package de.shokhor.costs.service;

import de.shokhor.costs.model.Cost;
import de.shokhor.costs.to.CostTo;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by user on 11.07.2017.
 */
public interface CostService {

    Cost save (CostTo costTo, int userId);

    Cost update (CostTo costTo, int userId);

    boolean delete (int costId, int userId);

    Cost get (int costId, int userId);

    List<Cost> getAll (int userId);

    List<Cost> getAllByGroup (int userId, int groupId);

    Cost save(Cost cost, int userId, int groupId);

    List<Cost> getFilteredList (int userId, Integer groupId, LocalDate startDate, LocalDate endDate);

    List<Cost> getBetween (int userId, LocalDate startDate, LocalDate endDate);

    LocalDate minDate(int userId);

    LocalDate maxDate(int userId);
}
