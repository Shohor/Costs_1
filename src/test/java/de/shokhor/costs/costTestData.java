package de.shokhor.costs;

import de.shokhor.costs.matcher.ModelMatcher;
import de.shokhor.costs.model.Cost;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;

/**
 * Created by user on 12.07.2017.
 */
public class costTestData {

    public static final ModelMatcher<Cost> MATCHER = ModelMatcher.of(Cost.class);

    public static final int COST_ID = 1;

    public static final Cost COST1 = new Cost(COST_ID +0,23.25,of(2017, Month.JANUARY, 1));
    public static final Cost COST2 = new Cost(COST_ID +1,55.45, of(2017, Month.JANUARY, 1));
    public static final Cost COST3 = new Cost(COST_ID +2,47.78,of(2017, Month.JANUARY, 2));
    public static final Cost COST4 = new Cost(COST_ID +3,8.21,of(2017, Month.JANUARY, 2));
    public static final Cost COST5 = new Cost(COST_ID +4,24.56,of(2017, Month.JANUARY, 3));
    public static final Cost COST_ADMIN1 = new Cost(COST_ID +5,11.66,of(2017, Month.JANUARY, 4));
    public static final Cost COST_ADMIN2 = new Cost(COST_ID +6,33.12,of(2017, Month.JANUARY, 4));

    public static final List<Cost> COSTS = Arrays.asList(COST1,COST2,COST3,COST4,COST5);

    public static final Cost getCreated()
    {
        return new Cost(null,35.56,of(2017, Month.JANUARY, 6),"New object");
    }

    public static final Cost getUpdate()
    {
        return new Cost(COST_ID +0,15.01, of(2017, Month.JANUARY, 1),"Update object");
    }
}
