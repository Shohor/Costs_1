package de.shokhor.costs.web.user;

import de.shokhor.costs.model.User.Role;
import de.shokhor.costs.model.User.User;
import de.shokhor.costs.service.UserService;
import de.shokhor.costs.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EnumSet;
import java.util.List;

public abstract class AbstractUserController {

    public static final Logger LOG = LoggerFactory.getLogger(AbstractUserController.class);

    @Autowired
    private UserService service;

    public void delete(int id)
    {
        LOG.info("Delete User{}", id);
        service.delete(id);
    }

    public User get(int id)
    {
        LOG.info("Get User{}",id);
        return service.get(id);
    }

    public User getByEmail(String email)
    {
        LOG.info("Get User by email{}", email);
        return service.getByEmail(email);
    }

    public List<User> getAll()
    {
        LOG.info("Get all users");
        return service.getAll();
    }

    public User create(User user)
    {
        user.setId(null);
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        LOG.info("Create user");
        return service.save(user);
    }

    public User update(User user, int id)
    {
        user.setId(id);
        LOG.info("Update User{}",id);
        return service.save(user);
    }

    public void enable(int id, boolean enabled) {
        LOG.info((enabled ? "enable " : "disable ") + id);
        service.enable(id, enabled);
    }
}
