package de.shokhor.costs.util;

import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.to.IncomeAndCostTo;
import org.apache.commons.collections.ListUtils;

import java.util.List;
import java.util.stream.Collectors;

public class IncomeAndCostUtil
{
    public static List<IncomeAndCostTo> transferIncomeAndCost(List<Cost> costs, List<Income> incomes)
    {
        return ListUtils.union(incomes.stream().map(IncomeAndCostTo::new).collect(Collectors.toList()), costs.stream().map(IncomeAndCostTo::new).collect(Collectors.toList()));

    }
}
