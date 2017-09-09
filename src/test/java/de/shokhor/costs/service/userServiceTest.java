package de.shokhor.costs.service;

import de.shokhor.costs.model.User.User;
import de.shokhor.costs.testService;
import de.shokhor.costs.util.PasswordUtil;
import de.shokhor.costs.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static de.shokhor.costs.userTestData.*;

/**
 * Created by user on 14.07.2017.
 */
public class userServiceTest extends testService {

    @Autowired
    protected UserService service;

    @Test
    public void testSave()
    {
        User userActual = getCreated();
        userActual.setPassword(PasswordUtil.encode(userActual.getPassword()));
        service.save(userActual);
        MATCHER.assertEquals(userActual,service.get(userActual.getId()));
    }

    @Test
    public void testUpdate()
    {
        User userActual = getUpdate();
        userActual.setPassword(PasswordUtil.encode(userActual.getPassword()));
        service.save(userActual);
        MATCHER.assertEquals(userActual,service.get(userActual.getId()));
    }

    @Test
    public void testDelete()
    {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Collections.singleton(USER),service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(5);
    }

    @Test
    public void testGet()
    {
        User userActual = service.get(USER_ID);
        MATCHER.assertEquals(userActual,ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(4);
    }

    @Test
    public void testGetAll()
    {
        List<User> users = service.getAll();
        MATCHER.assertCollectionEquals(USERS, users);
    }
    @Test
    public void testGetByEmail()
    {
        User userActual = service.getByEmail("shohor@mail.ru");
        MATCHER.assertEquals(userActual, ADMIN);
    }

    @Test
    public void testSetEnabledEquals() {
        service.enable(USER_ID, false);
        Assert.assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        Assert.assertTrue(service.get(USER_ID).isEnabled());
    }
}
