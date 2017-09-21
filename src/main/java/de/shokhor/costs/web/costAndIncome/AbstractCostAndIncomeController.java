package de.shokhor.costs.web.costAndIncome;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.model.Cost.TypeCost;
import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.service.CostService;
import de.shokhor.costs.service.TypeCostService;
import de.shokhor.costs.service.IncomeService;
import de.shokhor.costs.to.CostTo;
import de.shokhor.costs.to.IncomeTo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;


public abstract class AbstractCostAndIncomeController {

    public static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AbstractCostAndIncomeController.class);

    @Autowired
    private CostService costService;

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private TypeCostService typeCostService;

    public void costDelete(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Delete costAndIncome{} for User {}",id,userId);
        costService.delete(id,userId);
    }

    public void incomeDelete(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Delete costAndIncome{} for User {}",id,userId);
        incomeService.delete(id,userId);
    }

    public Cost getCost (int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get costAndIncome{} for User {}", id, userId);
        return costService.get(id,userId);
    }

    public Income getIncome (int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get costAndIncome{} for User {}", id, userId);
        return incomeService.get(id,userId);
    }

    public Cost create (CostTo costTo)
    {
        costTo.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("Create costAndIncome for User{}", userId);
        return costService.save(costTo,userId);
    }

    public Cost update(CostTo costTo)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Update costAndIncome{} for User {}", costTo.getId(), userId);
        return costService.update(costTo,userId);
    }

    public Income create (IncomeTo incomeTo)
    {
        incomeTo.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("Create Income for User{}", userId);
        return incomeService.save(incomeTo, userId);
    }

    public Income update(IncomeTo incomeTo)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Update Income{} for User {}", incomeTo.getId(), userId);
        return incomeService.update(incomeTo,userId);
    }

    /*public List<Cost> getAllByGroup(int groupId)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get all costs by group{} for User{}",groupId,userId);
        return costService.getAllByGroup(userId,groupId);
    }*/

    public List<Cost> getAllCosts()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get all costs for User{}", userId);
        return costService.getAll(userId);
    }

    public List<TypeCost> getAllGroups()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get all costs for User{}", userId);
        return typeCostService.getAll(userId);
    }

    public List<Income> getAllIncomes()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get all incomes for User{}", userId);
        return incomeService.getAll(userId);
    }

    /*public Cost update(Cost costAndIncome, int groupId)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Update costAndIncome{} for User {}", costAndIncome.getId(), userId);
        return costService.save(costAndIncome ,userId, groupId);
    }

    public Cost create(Cost costAndIncome, int groupId)
    {
        costAndIncome.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("Create costAndIncome for User{}", userId);
        return costService.save(costAndIncome,userId, groupId);
    }*/

    public List<Cost> filter (Integer groupId, LocalDate startDate, LocalDate endDate)
    {
        int userId=AuthorizedUser.id();
        LOG.info("Filtered costAndIncome for User{}, TypeCost{}, startDate{}, endDate{}", userId,groupId,startDate,endDate);
        return  costService.getFilteredList(userId, groupId, startDate, endDate);
    }

    public List<Cost> getBetweenCost (LocalDate startDate, LocalDate endDate)
    {
        int userId=AuthorizedUser.id();
        LOG.info("Filtered costAndIncome for User{}, startDate{}, endDate{}", userId,startDate,endDate);
        return  costService.getBetween(userId, startDate, endDate);
    }

    public List<Income> getBetweenIncome(LocalDate startDate, LocalDate endDate) {
        int userId=AuthorizedUser.id();
        LOG.info("Filtered costAndIncome for User{}, startDate{}, endDate{}", userId,startDate,endDate);
        return  incomeService.getBetween(userId, startDate, endDate);
    }

    public LocalDate minDateCost()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Minimum date for User{}", userId);
        return costService.minDate(userId);
    }

    public LocalDate maxDateCost()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Maximum date for User{}", userId);
        return costService.maxDate(userId);
    }

    public LocalDate minDateIncome()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Minimum date for User{}", userId);
        return incomeService.minDate(userId);
    }

    public LocalDate maxDateIncome()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Maximum date for User{}", userId);
        return incomeService.maxDate(userId);
    }

    public List<Income> getBetweenIncomeByType(Integer typeIncomeId, LocalDate startDate, LocalDate endDate) {
        int userId = AuthorizedUser.id();
        LOG.info("Get income for User{} and Type{}", userId, typeIncomeId);
        return incomeService.getBetweenByType(userId, typeIncomeId, startDate, endDate);
    }

    public List<Cost> getBetweenCostByType(Integer typeCostId, LocalDate startDate, LocalDate endDate) {
        int userId = AuthorizedUser.id();
        LOG.info("Get cost for User{} and Type{}", userId, typeCostId);
        return costService.getBetweenByType(userId, typeCostId, startDate, endDate);
    }

    public List<Income> getBetweenIncomeByTypeAndCards(Integer cashAccountsAndCardsId, Integer typeIncomeId, LocalDate startDate, LocalDate endDate) {
        int userId = AuthorizedUser.id();
        LOG.info("Get income for User{} and Type{} and CashAccountsAndCards{}", userId, typeIncomeId, cashAccountsAndCardsId);
        return incomeService.getBetweenByTypeAndCards(userId, cashAccountsAndCardsId, typeIncomeId, startDate, endDate);

    }

    public List<Cost> getBetweenCostByCards(Integer cashAccountsAndCardsId, LocalDate startDate, LocalDate endDate) {
        int userId = AuthorizedUser.id();
        LOG.info("Get cost for User{} and CashAccountsAndCards{}", userId, cashAccountsAndCardsId);
        return costService.getBetweenByCards(userId, cashAccountsAndCardsId, startDate, endDate);
    }

    public List<Income> getBetweenIncomeByCards(Integer cashAccountsAndCardsId, LocalDate startDate, LocalDate endDate) {
        int userId = AuthorizedUser.id();
        LOG.info("Get income for User{} and CashAccountsAndCards{}", userId, cashAccountsAndCardsId);
        return incomeService.getBetweenByCards(userId, cashAccountsAndCardsId, startDate, endDate);
    }

    public List<Cost> getBetweenCostByTypeAndCards(Integer cashAccountsAndCardsId, Integer typeCostId, LocalDate startDate, LocalDate endDate) {
        int userId = AuthorizedUser.id();
        LOG.info("Get income for User{} and Type{} and CashAccountsAndCards{}", userId, typeCostId, cashAccountsAndCardsId);
        return costService.getBetweenByTypeAndCards(userId, cashAccountsAndCardsId, typeCostId, startDate, endDate);
    }
}
