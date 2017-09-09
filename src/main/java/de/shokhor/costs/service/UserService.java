package de.shokhor.costs.service;

import de.shokhor.costs.model.User.User;

import java.util.List;

/**
 * Created by user on 11.07.2017.
 */
public interface UserService {
    User save (User user);

    User update (User user);

    void delete (int userId);

    User get (int userId);

    User getByEmail (String email);

    void enable(int id, boolean enable);

    List<User> getAll ();
}
