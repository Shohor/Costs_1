package de.shokhor.costs.util;

import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.to.CostTo;

public class CostUtil {

    public static Cost createFromCostTo(CostTo costTo)
    {
        Cost cost = new Cost(null, costTo.getPrice(), costTo.getDate(), costTo.getDescription());
        return cost;
    }
}
