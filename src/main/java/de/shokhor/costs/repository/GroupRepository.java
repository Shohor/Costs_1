package de.shokhor.costs.repository;

import de.shokhor.costs.model.Group;

import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
public interface GroupRepository
{
    Group save (Group group);

    boolean delete (int groupId);

    Group get (int groupId);

    List<Group> getAll ();
}
