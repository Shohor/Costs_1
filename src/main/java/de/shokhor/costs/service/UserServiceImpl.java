package de.shokhor.costs.service;

import de.shokhor.costs.model.User;
import de.shokhor.costs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 12.07.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(int userId) {
        return repository.delete(userId);
    }

    @Override
    public User get(int userId) {
        return repository.get(userId);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
