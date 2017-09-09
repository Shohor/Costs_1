package de.shokhor.costs.service;

import de.shokhor.costs.model.Cost.TypeCost;

import java.util.List;

/**
 * Created by user on 11.07.2017.
 */
public interface GroupService {
    TypeCost save (TypeCost typeCost, int userId);

    TypeCost update (TypeCost typeCost, int userId);

    void delete (int groupId, int userId);

    TypeCost get (int groupId, int userId);

    List<TypeCost> getAll (int userId);
}
