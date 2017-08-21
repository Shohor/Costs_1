package de.shokhor.costs.service;

import de.shokhor.costs.model.Cost;
import de.shokhor.costs.repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 12.07.2017.
 */
@Service
public class CostServiceImpl implements CostService {

    @Autowired
    private CostRepository repository;

    @Override
    public Cost save(Cost cost, int userId, int groupId) {
        return repository.save(cost,userId,groupId);
    }

    @Override
    public Cost update(Cost cost, int userId, int groupId) {
        return repository.save(cost,userId,groupId);
    }

    @Override
    public boolean delete(int costId, int userId) {
        return repository.delete(costId,userId);
    }

    @Override
    public Cost get(int costId, int userId) {
        return repository.get(costId, userId);
    }

    @Override
    public List<Cost> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Cost> getAllByGroup(int userId, int groupId) {
        return repository.getAllByGroup(userId, groupId);
    }
}
