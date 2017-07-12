package de.shokhor.costs.service;

import de.shokhor.costs.model.Group;
import de.shokhor.costs.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 12.07.2017.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    public GroupRepository repository;

    @Override
    public Group save(Group group, int userId) {
        return repository.save(group, userId);
    }

    @Override
    public Group update(Group group, int userId) {
        return repository.save(group, userId);
    }

    @Override
    public boolean delete(int groupId, int userId) {
        return repository.delete(groupId, userId);
    }

    @Override
    public Group get(int groupId, int userId) {
        return repository.get(groupId, userId);
    }

    @Override
    public List<Group> getAll(int userId) {
        return repository.getAll(userId);
    }
}
