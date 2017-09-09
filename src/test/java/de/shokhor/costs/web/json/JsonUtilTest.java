package de.shokhor.costs.web.json;

import de.shokhor.costs.costTestData;
import de.shokhor.costs.model.Cost.Cost;
import org.junit.Test;


import java.util.List;

/**
 * GKislin
 * 22.07.2015.
 */
public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(costTestData.COST_ADMIN1);
        System.out.println(json);
        Cost userCost = JsonUtil.readValue(json, Cost.class);
        costTestData.MATCHER.assertEquals(costTestData.COST_ADMIN1, userCost);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(costTestData.COSTS);
        System.out.println(json);
        List<Cost> userCosts = JsonUtil.readValues(json, Cost.class);
        costTestData.MATCHER.assertCollectionEquals(costTestData.COSTS, userCosts);
    }
}