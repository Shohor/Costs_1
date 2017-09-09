package de.shokhor.costs.service;

import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.testService;
import de.shokhor.costs.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static de.shokhor.costs.cashAccountsAndCardsTestData.CASH_ACCOUNTS_AND_CARDS_ID;
import static de.shokhor.costs.incomeTestDate.*;
import static de.shokhor.costs.userTestData.USER_ID;

public class incomeServiceTest extends testService {

    @Autowired
    protected IncomeService service;

    @Test
    public void testSave()
    {
        Income incomeActual = getCreated();
        service.save(incomeActual, CASH_ACCOUNTS_AND_CARDS_ID+1, 1, USER_ID);
        MATCHER.assertEquals(incomeActual,service.get(incomeActual.getId(),USER_ID));
    }

    @Test
    public void testUpdate()
    {
        Income incomeActual = getUpdate();
        service.save(incomeActual, CASH_ACCOUNTS_AND_CARDS_ID+1,1, USER_ID);
        MATCHER
                .assertEquals(incomeActual ,service.get(incomeActual.getId(),USER_ID));
    }

    @Test
    public void testDelete()
    {
        service.delete(INCOME_ID,USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(INCOME_2, INCOME_3),service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(8, 4);
    }

    @Test
    public void testGet()
    {
        Income costActual = service.get(INCOME_ID,USER_ID);
        MATCHER.assertEquals(costActual, INCOME_1);
    }

    /*@Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(8, 5);
    }*/

    @Test
    public void testGetAll()
    {
        List<Income> incomesActual = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(INCOMES ,incomesActual);

    }
}
