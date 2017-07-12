package de.shokhor.costs.repository;

import de.shokhor.costs.model.Group;

import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
public interface GroupRepository
{
    Group save (Group group, int userId);

    boolean delete (int groupId, int userId);

    Group get (int groupId, int userId);

    List<Group> getAll (int userId);
}
