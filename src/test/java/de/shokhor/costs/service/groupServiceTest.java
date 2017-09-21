package de.shokhor.costs.service;

import de.shokhor.costs.model.Cost.TypeCost;
import de.shokhor.costs.testService;
import de.shokhor.costs.util.exception.NotFoundException;
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
    protected TypeCostService service;

    @Test
    public void testSave()
    {
        TypeCost typeCostActual = getCreated();
        service.save(typeCostActual,USER_ID);
        MATCHER.assertEquals(typeCostActual,service.get(typeCostActual.getId(),USER_ID));
    }

    @Test
    public void testUpdate()
    {
        TypeCost typeCostActual = getUpdate();
        service.save(typeCostActual,USER_ID);
        MATCHER.assertEquals(typeCostActual,service.get(typeCostActual.getId(),USER_ID));
    }

    @Test
    public void testDelete()
    {
        service.delete(GROUP_ID,USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(COST_GROUP_2, COST_GROUP_4),service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(8, 4);
    }

    @Test
    public void testGet()
    {
        TypeCost typeCostActual = service.get(GROUP_ID,USER_ID);
        MATCHER.assertEquals(typeCostActual, COST_GROUP_1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(8, 5);
    }

    @Test
    public void testGetAll()
    {
        List<TypeCost> groupsActual = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(COST_GROUPS,groupsActual);

    }
}
