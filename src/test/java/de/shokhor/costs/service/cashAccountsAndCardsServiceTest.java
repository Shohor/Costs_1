package de.shokhor.costs.service;

import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.testService;
import de.shokhor.costs.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;


import static de.shokhor.costs.cashAccountsAndCardsTestData.*;
import static de.shokhor.costs.userTestData.USER_ID;

public class cashAccountsAndCardsServiceTest extends testService {

    @Autowired
    protected CashAccountsAndCardsService service;

    @Test
    public void testSave()
    {
        CashAccountsAndCards cashAccountsAndCardsActual = getCreated();
        service.save(cashAccountsAndCardsActual,USER_ID);
        MATCHER.assertEquals(cashAccountsAndCardsActual,service.get(cashAccountsAndCardsActual.getId(),USER_ID));
    }

    @Test
    public void testUpdate()
    {
        CashAccountsAndCards cashAccountsAndCardsActual = getUpdate();
        service.save(cashAccountsAndCardsActual,USER_ID);
        MATCHER.assertEquals(cashAccountsAndCardsActual,service.get(cashAccountsAndCardsActual.getId(),USER_ID));
    }

    @Test
    public void testDelete()
    {
        service.delete(CASH_ACCOUNTS_AND_CARDS_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(CASH_ACCOUNTS_AND_CARDS_2),service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(8, 5);
    }

    @Test
    public void testGet()
    {
        CashAccountsAndCards cashAccountsAndCardsActual = service.get(CASH_ACCOUNTS_AND_CARDS_ID,USER_ID);
        MATCHER.assertEquals(cashAccountsAndCardsActual, CASH_ACCOUNTS_AND_CARDS_1);
    }

    /*@Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(8, 5);
    }*/

    @Test
    public void testGetAll()
    {
        List<CashAccountsAndCards> cashAccountsAndCardsActual = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(CASH_ACCOUNTS_AND_CARDS,cashAccountsAndCardsActual);

    }
}
