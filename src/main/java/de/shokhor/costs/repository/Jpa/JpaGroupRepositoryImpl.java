package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.Cost.TypeCost;
import de.shokhor.costs.model.User.User;
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
@Transactional(readOnly = true)
public class JpaGroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public TypeCost save(TypeCost typeCost, int userId)
    {
        typeCost.setUser(em.getReference(User.class, userId));
        if (typeCost.isNew())
        {
            em.persist(typeCost);
            return typeCost;
        }
        else
        {
            return em.merge(typeCost);
        }
    }

    @Override
    @Transactional
    public boolean delete(int groupId, int userId)
    {
        return em.createNamedQuery(TypeCost.DELETE)
                .setParameter("id", groupId)
                .setParameter("userId", userId)
                .executeUpdate()!=0;
    }

    @Override
    public TypeCost get(int groupId, int userId)
    {

        TypeCost typeCost = em.find(TypeCost.class, groupId);
        return typeCost !=null && typeCost.getUser().getId()==userId ? typeCost : null;
    }

    public List<TypeCost> getAll(int userId)
    {
        return em.createNamedQuery(TypeCost.ALL,TypeCost.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
