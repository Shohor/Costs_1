package de.shokhor.costs.repository;

import de.shokhor.costs.model.Cost.TypeCost;

import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
public interface GroupRepository
{
    TypeCost save (TypeCost typeCost, int userId);

    boolean delete (int groupId, int userId);

    TypeCost get (int groupId, int userId);

    List<TypeCost> getAll (int userId);
}
