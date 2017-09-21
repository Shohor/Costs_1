package de.shokhor.costs.web.type;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.model.Cost.TypeCost;
import de.shokhor.costs.model.Income.TypeIncome;
import de.shokhor.costs.service.TypeCostService;
import de.shokhor.costs.service.TypeIncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractTypeController {

    public static final Logger LOG = LoggerFactory.getLogger(AbstractTypeController.class);

    @Autowired
    private TypeCostService typeCostService;

    @Autowired
    private TypeIncomeService typeIncomeService;

    public void deleteCost(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Delete groupCost{} for user{}", id, userId);
        typeCostService.delete(id,userId);
    }

    public void deleteIncome(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Delete groupIncome{} for user{}", id, userId);
        typeIncomeService.delete(id,userId);
    }

    public TypeCost getCost(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get groupCost{} for User{}", id, userId);
        return typeCostService.get(id,userId);
    }

    public TypeIncome getIncome(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get groupIncome{} for User{}", id, userId);
        return typeIncomeService.get(id,userId);
    }

    public List<TypeCost> getAllTypeCost()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get all type of costs for User{}", userId);
        return typeCostService.getAll(userId);
    }

    public List<TypeIncome> getAllTypeIncome()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get all type of incomes for User{}", userId);
        return typeIncomeService.getAll(userId);
    }

    public TypeCost createCost (TypeCost typeCost)
    {
        typeCost.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("Create typeCost for User{}", userId);
        return typeCostService.save(typeCost,userId);
    }

    public TypeIncome createIncome (TypeIncome typeIncome)
    {
        typeIncome.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("Create typeIncome for User{}", userId);
        return typeIncomeService.save(typeIncome,userId);
    }

    public TypeCost updateCost(TypeCost typeCost)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Update typeCost{} for User{}", typeCost.getId(), userId);
        return typeCostService.save(typeCost, userId);
    }

    public TypeIncome updateIncome(TypeIncome typeIncome)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Update typeIncome{} for User{}", typeIncome.getId(), userId);
        return typeIncomeService.save(typeIncome, userId);
    }
}
