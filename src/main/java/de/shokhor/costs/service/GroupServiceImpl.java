package de.shokhor.costs.service;

import de.shokhor.costs.model.CostGroup;
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
    public CostGroup save(CostGroup costGroup, int userId) {
        return repository.save(costGroup, userId);
    }

    @Override
    public CostGroup update(CostGroup costGroup, int userId) {
        return repository.save(costGroup, userId);
    }

    @Override
    public boolean delete(int groupId, int userId) {
        return repository.delete(groupId, userId);
    }

    @Override
    public CostGroup get(int groupId, int userId) {
        return repository.get(groupId, userId);
    }

    @Override
    public List<CostGroup> getAll(int userId) {
        return repository.getAll(userId);
    }
}
