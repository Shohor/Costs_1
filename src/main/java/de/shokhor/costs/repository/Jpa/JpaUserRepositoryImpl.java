package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.User;
import de.shokhor.costs.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
@Repository
public class JpaUserRepositoryImpl implements UserRepository {

    private EntityManager em;

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

    public boolean delete(int userId)
    {
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", userId)
                .executeUpdate()!=0;
    }

    public User get(int userId)
    {
        return em.find(User.class, userId);
    }

    public User getByEmail(String email)
    {
        return em.createNamedQuery(User.BY_EMAIL,User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<User> getAll()
    {
        return em.createNamedQuery(User.ALL_SORTED, User.class)
                .getResultList();
    }
}
