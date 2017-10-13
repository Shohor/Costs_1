package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.model.TypeCashAccountsAndCards;
import de.shokhor.costs.model.User.User;
import de.shokhor.costs.repository.CashAccountsAndCardsRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public class JpaCashAccountsAndCardsImpl implements CashAccountsAndCardsRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public CashAccountsAndCards save(CashAccountsAndCards cashAccountsAndCards, int userId) {
        cashAccountsAndCards.setUser(em.getReference(User.class, userId));
        if (cashAccountsAndCards.isNew())
        {
            em.persist(cashAccountsAndCards);
            return cashAccountsAndCards;
        }
        else
        {
            em.merge(cashAccountsAndCards);
            return cashAccountsAndCards;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(CashAccountsAndCards.DELETE)
                .setParameter("userId", userId)
                .setParameter("id", id)
                .executeUpdate()!=0;
    }

    @Override
    public CashAccountsAndCards get(int id, int userId) {
        return em.createNamedQuery(CashAccountsAndCards.GET, CashAccountsAndCards.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public List<CashAccountsAndCards> getAll(int userId) {
        return em.createNamedQuery(CashAccountsAndCards.GET_ALL, CashAccountsAndCards.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Double summIncome(int id, int userId) {
        Double summ = em.createNamedQuery(CashAccountsAndCards.SUMM_INCOME, Double.class)
                .setParameter("userId", userId)
                .setParameter("id", id)
                .getSingleResult();
        if (summ==null){summ=0.0;}
        return summ;
    }

    @Override
    public Double summCost(int id, int userId) {
        Double summ = em.createNamedQuery(CashAccountsAndCards.SUMM_COST, Double.class)
                .setParameter("userId", userId)
                .setParameter("id", id)
                .getSingleResult();
        if (summ==null) {summ=0.0;}
        return summ;
    }
}
