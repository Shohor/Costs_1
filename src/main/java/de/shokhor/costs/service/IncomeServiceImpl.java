package de.shokhor.costs.service;

import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.repository.IncomeRepository;
import de.shokhor.costs.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService{

    @Autowired
    private IncomeRepository repository;

    @Override
    public Income save(Income income, int cashAccountsAndCards_id, int typeIncome_id, int userId) {
        Assert.notNull(income, "Income must be not null");
        return repository.save(income, cashAccountsAndCards_id, typeIncome_id, userId);
    }

    @Override
    public Income update(Income income, int cashAccountsAndCards_id, int typeIncome_id, int userId) {
        Assert.notNull(income, "Income must be not null");
        return ExceptionUtil.checkNotFoundWithId(repository.save(income, cashAccountsAndCards_id, typeIncome_id, userId), income.getId());
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
}
