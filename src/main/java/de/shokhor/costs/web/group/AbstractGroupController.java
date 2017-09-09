package de.shokhor.costs.web.group;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.model.Cost.TypeCost;
import de.shokhor.costs.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractGroupController {

    public static final Logger LOG = LoggerFactory.getLogger(AbstractGroupController.class);

    @Autowired
    private GroupService service;

    public void delete(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Delete group{} for user{}", id, userId);
        service.delete(id,userId);
    }

    public TypeCost get(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get group{} for User{}", id, userId);
        return service.get(id,userId);
    }

    public List<TypeCost> getAll()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get all groups for User{}", userId);
        return service.getAll(userId);
    }

    public TypeCost create (TypeCost typeCost)
    {
        typeCost.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("Create typeCost for User{}", userId);
        return service.save(typeCost,userId);
    }

    public TypeCost update(TypeCost typeCost)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Update typeCost{} for User{}", typeCost.getId(), userId);
        return service.save(typeCost, userId);
    }
}
