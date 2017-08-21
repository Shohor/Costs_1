package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.Cost;
import de.shokhor.costs.model.Group;
import de.shokhor.costs.model.User;
import de.shokhor.costs.repository.CostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
@Repository
public class JpaCostRepositoryImpl implements CostRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Cost save(Cost cost, int userId, int groupId) {

        cost.setGroup(em.getReference(Group.class,groupId));
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
}
