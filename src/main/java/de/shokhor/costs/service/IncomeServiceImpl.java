package de.shokhor.costs.service;

import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.repository.IncomeRepository;
import de.shokhor.costs.to.IncomeTo;
import de.shokhor.costs.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService{

    @Autowired
    private IncomeRepository repository;

    @Override
    public Income save(Income income, int userId) {
        Assert.notNull(income, "Income must be not null");
        return repository.save(income, userId);
    }

    @Override
    public Income update(Income income, int userId) {
        Assert.notNull(income, "Income must be not null");
        return ExceptionUtil.checkNotFoundWithId(repository.save(income, userId), income.getId());
    }

    @Override
    public void delete(int id, int userId) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId),id);
    }

    @Override
    public Income get(int id, int userId) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, userId),id);
    }

    @Override
    public List<Income> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Income save(IncomeTo incomeTo, int userId) {
        Assert.notNull(incomeTo, "Income must be not null");
        return repository.save(incomeTo, userId);
    }

    @Override
    public Income update(IncomeTo incomeTo, int userId) {
        Assert.notNull(incomeTo, "Income must be not null");
        return ExceptionUtil.checkNotFoundWithId(repository.save(incomeTo, userId), incomeTo.getId());
    }

    @Override
    public List<Income> getAllByGroup(int userId, int typeIncomeId) {
        return repository.getAllByGroup(userId, typeIncomeId);
    }

    @Override
    public List<Income> getFilteredList(int userId, Integer typeIncomeId, LocalDate startDate, LocalDate endDate) {
        return repository.getFilteredList(userId, typeIncomeId, startDate, endDate);
    }

    @Override
    public List<Income> getBetween(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetween(userId, startDate, endDate);
    }

    /*@Cacheable("date")*/
    @Override
    public LocalDate minDate(int userId) {
        return repository.minDate(userId);
    }

    /*@Cacheable("date")*/
    @Override
    public LocalDate maxDate(int userId) {
        return repository.maxDate(userId);
    }

    @Override
    public List<Income> getBetweenByType(int userId, Integer typeIncomeId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetweenByType(userId, typeIncomeId, startDate, endDate);
    }

    @Override
    public List<Income> getBetweenByTypeAndCards(int userId, Integer cashAccountsAndCardsId, Integer typeIncomeId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetweenByTypeAndCards(userId, cashAccountsAndCardsId, typeIncomeId, startDate, endDate);
    }

    @Override
    public List<Income> getBetweenByCards(int userId, Integer cashAccountsAndCardsId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetweenByCards(userId, cashAccountsAndCardsId, startDate, endDate);
    }
}
