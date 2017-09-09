package de.shokhor.costs;

import de.shokhor.costs.matcher.ModelMatcher;
import de.shokhor.costs.model.Income.Income;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;

public class incomeTestDate {

    public static final ModelMatcher<Income> MATCHER = ModelMatcher.of(Income.class);

    public static final int INCOME_ID = 1;

    public static final Income INCOME_1 = new Income(INCOME_ID+0,250,of(2017, Month.JANUARY, 1),"");
    public static final Income INCOME_2 = new Income(INCOME_ID+1,550,of(2017, Month.JANUARY, 2),"");
    public static final Income INCOME_3 = new Income(INCOME_ID+2,100,of(2017, Month.JANUARY, 1),"");

    public static final List<Income> INCOMES = Arrays.asList(INCOME_1, INCOME_2, INCOME_3);

    public static final Income getCreated()
    {
        return new Income(null,100,of(2017, Month.JANUARY, 6),"New object");
    }

    public static final Income getUpdate()
    {
        return new Income(INCOME_ID+0,251, of(2017, Month.JANUARY, 1),"Update object");
    }
}
