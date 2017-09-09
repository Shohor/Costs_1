package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.model.Cost.TypeCost;
import de.shokhor.costs.model.User.User;
import de.shokhor.costs.repository.CostRepository;
import de.shokhor.costs.to.CostTo;
import de.shokhor.costs.util.CostUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaCostRepositoryImpl implements CostRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Cost save(CostTo costTo, int userId) {

        Cost cost=CostUtil.createFromCostTo(costTo);
        cost.setTypeCost(em.getReference(TypeCost.class,costTo.getTypeId()));
        cost.setUser(em.getReference(User.class, userId));
        if (cost.isNew())
        {
            em.persist(cost);
            return cost;
        }
        else {
            return em.merge(cost);
        }
    }

    @Override
    @Transactional
    public Cost save(Cost cost, int userId, int groupId, int cashAccountsAndCardsId) {
        cost.setTypeCost(em.getReference(TypeCost.class, groupId));
        cost.setUser(em.getReference(User.class, userId));
        cost.setCashAccountsAndCards(em.getReference(CashAccountsAndCards.class, cashAccountsAndCardsId));
        if (cost.isNew())
        {
            em.persist(cost);
            return cost;
        }
        else {
            return em.merge(cost);
        }
    }

    @Override
    public Cost save(Cost cost, int userId, int groupId) {
        cost.setTypeCost(em.getReference(TypeCost.class, groupId));
        cost.setUser(em.getReference(User.class, userId));
        cost.setCashAccountsAndCards(null);
        if (cost.isNew())
        {
            em.persist(cost);
            return cost;
        }
        else {
            return em.merge(cost);
        }
    }

    @Override
    @Transactional
    public boolean delete(int costId, int userId)
    {
        return em.createNamedQuery(Cost.DELETE)
                .setParameter("id",costId)
                .setParameter("userId", userId)
                .executeUpdate()!=0;
    }

    public Cost get(int costId, int userId) {
        Cost cost = em.find(Cost.class, costId);
        return cost!=null && cost.getUser().getId()==userId ? cost : null;
    }

    public List<Cost> getAll(int userId) {
        return em.createNamedQuery(Cost.ALL_SORTED,Cost.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    public List<Cost> getAllByGroup(int userId, int groupId)
    {
        return em.createNamedQuery(Cost.BY_GROUP,Cost.class)
                .setParameter("userId", userId)
                .setParameter("groupId",groupId)
                .getResultList();
    }

    @Override
    public List<Cost> getFilteredList(int userId, Integer groupId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Cost.FILTER,Cost.class)
                .setParameter("userId", userId)
                .setParameter("groupId", groupId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public List<Cost> getBetween(int userId, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Cost.GET_BETWEEN,Cost.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public LocalDate minDate(int userId) {
        return em.createNamedQuery(Cost.MIN_DATE, LocalDate.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public LocalDate maxDate(int userId) {
        return em.createNamedQuery(Cost.MAX_DATE, LocalDate.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }
}
