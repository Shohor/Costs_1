package de.shokhor.costs.repository;

import de.shokhor.costs.model.User.User;

import java.util.List;

/**
 * Created by user on 10.07.2017.
 */
public interface UserRepository {

    User save (User user);

    boolean delete (int userId);

    User get (int userId);

    User getByEmail (String email);

    List<User> getAll ();
}
