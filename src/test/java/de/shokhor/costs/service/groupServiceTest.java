package de.shokhor.costs.service;

import de.shokhor.costs.model.Group;
import de.shokhor.costs.testService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static de.shokhor.costs.groupTestData.*;
import static de.shokhor.costs.userTestData.USER_ID;

/**
 * Created by user on 14.07.2017.
 */
public class groupServiceTest extends testService {

    @Autowired
    protected GroupService service;

    @Test
    public void testSave()
    {
        Group groupActual = getCreated();
        service.save(groupActual,USER_ID);
        MATCHER.assertEquals(groupActual,service.get(groupActual.getId(),USER_ID));
    }

    @Test
    public void testUpdate()
    {
        Group groupActual = getUpdate();
        service.save(groupActual,USER_ID);
        MATCHER.assertEquals(groupActual,service.get(groupActual.getId(),USER_ID));
    }

    @Test
    public void testDelete()
    {
        service.delete(GROUP_ID,USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(GROUP2,GROUP4),service.getAll(USER_ID));
    }

    @Test
    public void testGet()
    {
        Group groupActual = service.get(GROUP_ID,USER_ID);
        MATCHER.assertEquals(groupActual,GROUP1);
    }

    @Test
    public void testGetAll()
    {
        List<Group> groupsActual = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(GROUPS,groupsActual);

    }
}
