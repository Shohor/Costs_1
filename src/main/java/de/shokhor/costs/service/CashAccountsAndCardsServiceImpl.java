package de.shokhor.costs.service;

import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.repository.CashAccountsAndCardsRepository;
import de.shokhor.costs.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class CashAccountsAndCardsServiceImpl implements CashAccountsAndCardsService {

    @Autowired
    CashAccountsAndCardsRepository repository;

    @Override
    public CashAccountsAndCards save(CashAccountsAndCards cashAccountsAndCards, int userId) {
        Assert.notNull(cashAccountsAndCards,"Cash, accounts order cards must be not null");
        return repository.save(cashAccountsAndCards, userId);
    }

    @Override
    public CashAccountsAndCards update(CashAccountsAndCards cashAccountsAndCards, int userId) {
        Assert.notNull(cashAccountsAndCards, "Cash, accounts order cards must be not null");
        return ExceptionUtil.checkNotFoundWithId(repository.save(cashAccountsAndCards, userId),cashAccountsAndCards.getId());
    }

    @Override
    public void delete(int id, int userId) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public CashAccountsAndCards get(int id, int userId) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<CashAccountsAndCards> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Double summIncome(int id, int userId) {
        return repository.summIncome(id, userId);
    }

    @Override
    public Double summCost(int id, int userId) {
        return repository.summCost(id, userId);
    }
}
