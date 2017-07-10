package de.shokhor.costs.repository;

import de.shokhor.costs.model.Cost;

import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
public interface CostRepository
{
    Cost save (Cost cost, int userId);

    boolean delete (int costId, int userId);

    Cost get (int costId, int userId);

    List<Cost> getAll (int userId);

    List<Cost> getAllByGroup (int userId, int groupId);
}
