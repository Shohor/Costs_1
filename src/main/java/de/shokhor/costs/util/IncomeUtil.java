package de.shokhor.costs.util;

import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.to.IncomeTo;

import java.util.List;
import java.util.stream.Collectors;

public class IncomeUtil
{
    public static Income createFromCostTo(IncomeTo incomeTo)
    {
        Income income = new Income(incomeTo.getId(), incomeTo.getAmount(), incomeTo.getDate(), incomeTo.getDescription());
        return income;
    }

    public static List<IncomeTo> createCostTofromCost (List<Income> incomes)
    {
        return incomes.stream().map(IncomeTo::new).collect(Collectors.toList());
    }
}
