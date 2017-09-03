package de.shokhor.costs.repository;

import de.shokhor.costs.model.CostGroup;

import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
public interface GroupRepository
{
    CostGroup save (CostGroup costGroup, int userId);

    boolean delete (int groupId, int userId);

    CostGroup get (int groupId, int userId);

    List<CostGroup> getAll (int userId);
}
