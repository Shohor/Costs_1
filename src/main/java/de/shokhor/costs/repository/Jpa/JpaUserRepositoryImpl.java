package de.shokhor.costs.repository.Jpa;

import de.shokhor.costs.model.User;
import de.shokhor.costs.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
@Repository
public class JpaUserRepositoryImpl implements UserRepository {
    public User save(User user) {
        return null;
    }

    public boolean delete(int userId) {
        return false;
    }

    public User get(int userId) {
        return null;
    }

    public User getByEmail(String email) {
        return null;
    }

    public List<User> getAll() {
        return null;
    }
}
