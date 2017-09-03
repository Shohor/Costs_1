package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.CostGroup;
import de.shokhor.costs.model.User;
import de.shokhor.costs.repository.GroupRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
@Repository
public class JpaGroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public CostGroup save(CostGroup costGroup, int userId)
    {
        costGroup.setUser(em.getReference(User.class, userId));
        if (costGroup.isNew())
        {
            em.persist(costGroup);
            return costGroup;
        }
        else
        {
            return em.merge(costGroup);
        }
    }

    @Transactional
    public boolean delete(int groupId, int userId)
    {
        return em.createNamedQuery(CostGroup.DELETE)
                .setParameter("id", groupId)
                .setParameter("userId", userId)
                .executeUpdate()!=0;
    }

    public CostGroup get(int groupId, int userId)
    {

        CostGroup costGroup = em.find(CostGroup.class, groupId);
        return costGroup !=null && costGroup.getUser().getId()==userId ? costGroup : null;
    }

    public List<CostGroup> getAll(int userId)
    {
        return em.createNamedQuery(CostGroup.ALL,CostGroup.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
