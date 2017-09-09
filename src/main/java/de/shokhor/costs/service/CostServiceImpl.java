package de.shokhor.costs.service;

import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.repository.CostRepository;
import de.shokhor.costs.to.CostTo;
import de.shokhor.costs.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by user on 12.07.2017.
 */
@Service
public class CostServiceImpl implements CostService {

    @Autowired
    private CostRepository repository;

    @Override
    public Cost save(CostTo costTo, int userId)
    {
        Assert.notNull(costTo,"Cost must be not null");
        return repository.save(costTo,userId);
    }

    @Override
    public Cost update(CostTo costTo, int userId)
    {
        Assert.notNull(costTo, "Cost must be not null");
        return ExceptionUtil.checkNotFoundWithId(repository.save(costTo,userId),costTo.getId());
    }

    @Override
    public void delete(int costId, int userId) {

        ExceptionUtil.checkNotFoundWithId(repository.delete(costId,userId),costId);
    }

    @Override
    public Cost get(int costId, int userId)
    {
        return ExceptionUtil.checkNotFoundWithId(repository.get(costId, userId), costId);
    }

    @Override
    public List<Cost> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Cost> getAllByGroup(int userId, int groupId) {
        return repository.getAllByGroup(userId, groupId);
    }

    @Override
    public Cost save(Cost cost, int userId, int groupId) {
        return repository.save(cost, userId, groupId);
    }

    @Override
    public Cost save(Cost cost, int userId, int groupId, int cashAccountsAndCardsId) {
        return repository.save(cost, userId, groupId, cashAccountsAndCardsId);
    }

    @Override
    public List<Cost> getFilteredList(int userId, Integer groupId, LocalDate startDate, LocalDate endDate) {
        return repository.getFilteredList(userId, groupId, startDate, endDate);
    }

    @Override
    public List<Cost> getBetween(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetween(userId, startDate, endDate);
    }
    @Cacheable("date")
    @Override
    public LocalDate minDate(int userId) {
        return repository.minDate(userId);
    }

    @Cacheable("date")
    @Override
    public LocalDate maxDate(int userId) {
        return repository.maxDate(userId);
    }
}
