package de.shokhor.costs.service;

import de.shokhor.costs.model.Cost;

import java.util.List;

/**
 * Created by user on 11.07.2017.
 */
public interface CostService {

    Cost save (Cost cost, int userId, int groupId);

    Cost update (Cost cost, int userId, int groupId);

    boolean delete (int costId, int userId);

    Cost get (int costId, int userId);

    List<Cost> getAll (int userId);

    List<Cost> getAllByGroup (int userId, int groupId);

}
