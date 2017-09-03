package de.shokhor.costs.service;

import de.shokhor.costs.model.CostGroup;
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
        CostGroup costGroupActual = getCreated();
        service.save(costGroupActual,USER_ID);
        MATCHER.assertEquals(costGroupActual,service.get(costGroupActual.getId(),USER_ID));
    }

    @Test
    public void testUpdate()
    {
        CostGroup costGroupActual = getUpdate();
        service.save(costGroupActual,USER_ID);
        MATCHER.assertEquals(costGroupActual,service.get(costGroupActual.getId(),USER_ID));
    }

    @Test
    public void testDelete()
    {
        service.delete(GROUP_ID,USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(COST_GROUP_2, COST_GROUP_4),service.getAll(USER_ID));
    }

    @Test
    public void testGet()
    {
        CostGroup costGroupActual = service.get(GROUP_ID,USER_ID);
        MATCHER.assertEquals(costGroupActual, COST_GROUP_1);
    }

    @Test
    public void testGetAll()
    {
        List<CostGroup> groupsActual = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(COST_GROUPS,groupsActual);

    }
}
