package de.shokhor.costs.web.cashAccountsAndCards;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.service.CashAccountsAndCardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractAccountsController {

    public static final Logger LOG = LoggerFactory.getLogger(AbstractAccountsController.class);


    @Autowired
    private CashAccountsAndCardsService cashAccountsAndCardsService;

    public List<CashAccountsAndCards> getAll()
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get accounts for User{}", userId);
        return cashAccountsAndCardsService.getAll(userId);
    }

    public void delete(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Delete account{} for user{}", id, userId);
        cashAccountsAndCardsService.delete(id,userId);
    }

    public CashAccountsAndCards get(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Get account{} for User{}", id, userId);
        return cashAccountsAndCardsService.get(id,userId);
    }

    public CashAccountsAndCards create (CashAccountsAndCards cashAccountsAndCards)
    {
        cashAccountsAndCards.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("Create account for User{}", userId);
        return cashAccountsAndCardsService.save(cashAccountsAndCards,userId);
    }

    public CashAccountsAndCards update(CashAccountsAndCards cashAccountsAndCards)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Update account{} for User{}", cashAccountsAndCards.getId(), userId);
        return cashAccountsAndCardsService.save(cashAccountsAndCards, userId);
    }

    public Double summCost(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Summ cost account{} for User{}", id, userId);
        return cashAccountsAndCardsService.summCost(id, userId);

    }

    public Double summIncome(int id)
    {
        int userId = AuthorizedUser.id();
        LOG.info("Summ income account{} for User{}", id, userId);
        return cashAccountsAndCardsService.summIncome(id, userId);

    }
}
