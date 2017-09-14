package de.shokhor.costs.util;

import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.to.CostTo;

import java.util.List;
import java.util.stream.Collectors;

public class CostUtil {

    public static Cost createFromCostTo(CostTo costTo)
    {
        Cost cost = new Cost(costTo.getId(), costTo.getAmount(), costTo.getDate(), costTo.getDescription());
        return cost;
    }

    public static List<CostTo> createCostTofromCost (List<Cost> costs)
    {
        return costs.stream().map(CostTo::new).collect(Collectors.toList());
    }
}
