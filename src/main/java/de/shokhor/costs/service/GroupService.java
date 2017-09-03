package de.shokhor.costs.service;

import de.shokhor.costs.model.CostGroup;

import java.util.List;

/**
 * Created by user on 11.07.2017.
 */
public interface GroupService {
    CostGroup save (CostGroup costGroup, int userId);

    CostGroup update (CostGroup costGroup, int userId);

    boolean delete (int groupId, int userId);

    CostGroup get (int groupId, int userId);

    List<CostGroup> getAll (int userId);
}
