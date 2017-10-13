package de.shokhor.costs.service;

import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.testService;
import de.shokhor.costs.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static de.shokhor.costs.cashAccountsAndCardsTestData.CASH_ACCOUNTS_AND_CARDS_ID;
import static de.shokhor.costs.costTestData.*;
import static de.shokhor.costs.costTestData.getUpdate;
import static de.shokhor.costs.groupTestData.GROUP_ID;
import static de.shokhor.costs.userTestData.USER_ID;


public class costServiceTest extends testService {

    @Autowired
    protected CostService service;

    @Test
    public void testSave()
    {
        Cost costActual = getCreated();
        service.save(costActual, USER_ID, GROUP_ID, CASH_ACCOUNTS_AND_CARDS_ID);
        MATCHER.assertEquals(costActual,service.get(costActual.getId(),USER_ID));
    }

    @Test
    public void testUpdate()
    {
        Cost costActual = getUpdate();
        service.save(costActual, USER_ID, GROUP_ID, CASH_ACCOUNTS_AND_CARDS_ID);
        MATCHER.assertEquals(costActual,service.get(costActual.getId(),USER_ID));
    }

    @Test
    public void testDelete()
    {
        service.delete(COST_ID+5,USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(COST_ADMIN2),service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(8, 4);
    }

    @Test
    public void testGet()
    {
        Cost costActual = service.get(COST_ID+5,USER_ID);
        MATCHER.assertEquals(costActual, COST_ADMIN1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(8, 5);
    }

    @Test
    public void testGetAll()
    {
        List<Cost> costsActual = service.getAll(USER_ID+1);
        MATCHER.assertCollectionEquals(COSTS, costsActual);
    }
}
