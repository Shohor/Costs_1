package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.User.User;
import de.shokhor.costs.repository.UserRepository;
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
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        }
        else
        {
            return em.merge(user);
        }
    }

    @Override
    @Transactional
    public boolean delete(int userId)
    {
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", userId)
                .executeUpdate()!=0;
    }

    @Override
    public User get(int userId)
    {
        return em.find(User.class, userId);
    }

    @Override
    public User getByEmail(String email)
    {
        return em.createNamedQuery(User.BY_EMAIL,User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<User> getAll()
    {
        return em.createNamedQuery(User.ALL_SORTED, User.class)
                .getResultList();
    }
}
