package de.shokhor.costs.service;

import de.shokhor.costs.model.Cost.TypeCost;
import de.shokhor.costs.repository.GroupRepository;
import de.shokhor.costs.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by user on 12.07.2017.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    public GroupRepository repository;

    @Override
    public TypeCost save(TypeCost typeCost, int userId)
    {
        Assert.notNull(typeCost,"Group must be not null");
        return repository.save(typeCost, userId);
    }

    @Override
    public TypeCost update(TypeCost typeCost, int userId)
    {
        Assert.notNull(typeCost,"Group must be not null");
        return ExceptionUtil.checkNotFoundWithId(repository.save(typeCost, userId), typeCost.getId());
    }

    @Override
    public void delete(int groupId, int userId) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(groupId, userId), groupId);
    }

    @Override
    public TypeCost get(int groupId, int userId)
    {
        return ExceptionUtil.checkNotFoundWithId(repository.get(groupId, userId), groupId);
    }

    @Override
    public List<TypeCost> getAll(int userId) {
        return repository.getAll(userId);
    }
}
