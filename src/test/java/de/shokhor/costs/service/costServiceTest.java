package de.shokhor.costs.service;

import de.shokhor.costs.model.Cost;
import de.shokhor.costs.testService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static de.shokhor.costs.costTestData.*;
import static de.shokhor.costs.groupTestData.GROUP_ID;
import static de.shokhor.costs.userTestData.USER_ID;

/**
 * Created by user on 14.07.2017.
 */

public class costServiceTest extends testService{

    @Autowired
    protected CostServiceImpl service;

    @Test
    public void testDelete()
    {
        service.delete(COST_ID, USER_ID+1);
        MATCHER.assertCollectionEquals(Arrays.asList(COST2,COST3,COST4,COST5),service.getAll(USER_ID+1));
    }

    @Test
    public void testGet()
    {
        Cost costActual = service.get(COST_ID,USER_ID+1);
        MATCHER.assertEquals(COST1, costActual);
    }

    @Test
    public void testGetAll()
    {
        List<Cost> costsActual = service.getAll(USER_ID+1);
        MATCHER.assertCollectionEquals(COSTS, costsActual);
    }

    @Test
    public void testSave()
    {
        Cost created = getCreated();
        service.save(created,USER_ID+1, GROUP_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(COST1,COST2,COST3,COST4,COST5, created),service.getAll(USER_ID+1));

    }

    @Test
    public void testUpdate()
    {
        Cost update = getUpdate();
        service.save(update,USER_ID+1,GROUP_ID);
        MATCHER.assertEquals(update, service.get(update.getId(),USER_ID+1));
    }

    @Test
    public void testGetAllByGroup()
    {
        List<Cost> costsActual = service.getAllByGroup(USER_ID+1, GROUP_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(COST1,COST4,COST5),costsActual);
    }

}
