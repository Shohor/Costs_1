package de.shokhor.costs.service;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.model.User.User;
import de.shokhor.costs.repository.UserRepository;
import de.shokhor.costs.util.exception.ExceptionUtil;
import de.shokhor.costs.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by user on 12.07.2017.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    public UserRepository repository;

    @CacheEvict(value = "date", allEntries = true)
    @Override
    public User save(User user)
    {
        Assert.notNull(user,"User must be not null");
        return repository.save(user);
    }

    @CacheEvict(value = "date", allEntries = true)
    @Override
    public User update(User user)
    {

        return repository.save(user);
    }

    @CacheEvict(value = "date", allEntries = true)
    @Override
    public void delete(int userId)
    {
        ExceptionUtil.checkNotFoundWithId(repository.delete(userId),userId);
    }

    @Override
    public User get(int userId) throws NotFoundException
    {
        return ExceptionUtil.checkNotFoundWithId(repository.get(userId), userId);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException
    {
        Assert.notNull(email, "email must not be null");
        return ExceptionUtil.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "date", allEntries = true)
    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repository.getByEmail(email);
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(u);
    }
}
