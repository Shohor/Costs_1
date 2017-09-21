package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.model.Income.TypeIncome;
import de.shokhor.costs.model.User.User;
import de.shokhor.costs.repository.IncomeRepository;
import de.shokhor.costs.to.IncomeTo;
import de.shokhor.costs.util.IncomeUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaIncomeRepositoryImpl implements IncomeRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public Income save(Income income, int userId) {
        income.setCashAccountsAndCards(em.getReference(CashAccountsAndCards.class, income.getCashAccountsAndCards().getId()));
        income.setTypeIncome(em.getReference(TypeIncome.class, income.getTypeIncome().getId()));
        income.setUser(em.getReference(User.class, userId));
        if (income.isNew())
        {
            em.persist(income);
            return income;
        }
        else
        {
            em.merge(income);
            return income;
        }
    }

    @Override
    @Transactional
    public Income save(IncomeTo incomeTo, int userId)
    {
        Income income = IncomeUtil.createFromCostTo(incomeTo);
        income.setTypeIncome(em.getReference(TypeIncome.class, incomeTo.getTypeId()));
        income.setCashAccountsAndCards(em.getReference(CashAccountsAndCards.class, incomeTo.getCashAccountsAndCardsId()));
        income.setUser(em.getReference(User.class, userId));
        if (income.isNew())
        {
            em.persist(income);
            return income;
        }
        else
        {
            em.merge(income);
            return income;
        }
    }

    @Transactional
    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Income.DELETE)
                .setParameter("id", id)
                .setParameter("userId",userId)
                .executeUpdate()!=0;
    }

    @Override
    public Income get(int id, int userId) {
        return em.createNamedQuery(Income.GET, Income.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public List<Income> getAll(int userId) {
        return em.createNamedQuery(Income.GET_ALL, Income.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Income> getAllByGroup(int userId, int typeIncomeId) {
        return em.createNamedQuery(Income.BY_GROUP, Income.class)
                .setParameter("userId", userId)
                .setParameter("typeIncomeId", typeIncomeId)
                .getResultList();
    }

    @Override
    public List<Income> getFilteredList(int userId, Integer typeIncomeId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Income.FILTER, Income.class)
                .setParameter("userId", userId)
                .setParameter("typeIncomeId", typeIncomeId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public List<Income> getBetween(int userId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Income.GET_BETWEEN, Income.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public LocalDate minDate(int userId) {
        return em.createNamedQuery(Income.MIN_DATE, LocalDate.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public LocalDate maxDate(int userId) {
        return em.createNamedQuery(Income.MAX_DATE, LocalDate.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public List<Income> getBetweenByType(int userId, Integer typeIncomeId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Income.GET_BETWEEN_BY_TYPE, Income.class)
                .setParameter("userId", userId)
                .setParameter("typeIncomeId", typeIncomeId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public List<Income> getBetweenByTypeAndCards(int userId, Integer cashAccountsAndCardsId, Integer typeIncomeId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Income.GET_BETWEEN_BY_TYPE_CARDS, Income.class)
                .setParameter("userId", userId)
                .setParameter("typeIncomeId", typeIncomeId)
                .setParameter("cashAccountsAndCardsId", cashAccountsAndCardsId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public List<Income> getBetweenByCards(int userId, Integer cashAccountsAndCardsId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Income.GET_BETWEEN_BY_CARDS, Income.class)
                .setParameter("userId", userId)
                .setParameter("cashAccountsAndCardsId", cashAccountsAndCardsId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}
