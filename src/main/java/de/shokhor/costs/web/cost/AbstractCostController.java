package de.shokhor.costs.web.cost;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.model.Cost;
import de.shokhor.costs.service.CostService;
import de.shokhor.costs.service.GroupService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class AbstractCostController {

    public static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AbstractCostController.class);

    @Autowired
    private CostService costService;

    @Autowired
    private GroupService groupService;

    public void delete(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Delete cost{} for User {}",id,userId);
        costService.delete(id,userId);
    }

    public Cost get (int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get cost{} for User {}", id, userId);
        return costService.get(id,userId);
    }

    public Cost create (Cost cost, int groupId)
    {
        cost.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("Create cost for User{}", userId);
        return costService.save(cost,userId,groupId);
    }

    public Cost update(Cost cost, int id, int groupId)
    {
        cost.setId(id);
        int userId = AuthorizedUser.id();
        LOG.info("Update cost{} for User {}", id, userId);
        return costService.save(cost,userId,groupId);
    }

    public List<Cost> getAllByGroup(int groupId)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get all costs by group{} for User{}",groupId,userId);
        return costService.getAllByGroup(userId,groupId);
    }

    public List<Cost> getAll()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get all costs for User{}", userId);
        return costService.getAll(userId);
    }

}
