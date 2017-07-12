package de.shokhor.costs.service;

import de.shokhor.costs.model.Group;

import java.util.List;

/**
 * Created by user on 11.07.2017.
 */
public interface GroupService {
    Group save (Group group, int userId);

    Group update (Group group, int userId);

    boolean delete (int groupId, int userId);

    Group get (int groupId, int userId);

    List<Group> getAll (int userId);
}
