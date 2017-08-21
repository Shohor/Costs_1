package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.Group;
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
    public Group save(Group group, int userId)
    {
        group.setUser(em.getReference(User.class, userId));
        if (group.isNew())
        {
            em.persist(group);
            return group;
        }
        else
        {
            return em.merge(group);
        }
    }

    @Transactional
    public boolean delete(int groupId, int userId)
    {
        return em.createNamedQuery(Group.DELETE)
                .setParameter("id", groupId)
                .setParameter("userId", userId)
                .executeUpdate()!=0;
    }

    public Group get(int groupId, int userId)
    {

        Group group = em.find(Group.class, groupId);
        return group!=null && group.getUser().getId()==userId ? group: null;
    }

    public List<Group> getAll(int userId)
    {
        return em.createNamedQuery(Group.ALL,Group.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
