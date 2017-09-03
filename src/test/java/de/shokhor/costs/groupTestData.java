package de.shokhor.costs;

import de.shokhor.costs.matcher.ModelMatcher;
import de.shokhor.costs.model.CostGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 12.07.2017.
 */
public class groupTestData {

    public static final ModelMatcher<CostGroup> MATCHER = ModelMatcher.of(CostGroup.class);

    public static final int GROUP_ID = 1;

    public static final CostGroup COST_GROUP_1 = new CostGroup(GROUP_ID +0, "Food");
    public static final CostGroup COST_GROUP_2 = new CostGroup(GROUP_ID +1, "Children");
    public static final CostGroup COST_GROUP_3 = new CostGroup(GROUP_ID +2, "House");
    public static final CostGroup COST_GROUP_4 = new CostGroup(GROUP_ID +3, "Presents");
    public static final CostGroup COST_GROUP_5 = new CostGroup(GROUP_ID +4, "Food");

    public static final List<CostGroup> COST_GROUPS = Arrays.asList(COST_GROUP_1, COST_GROUP_2, COST_GROUP_4);

    public static final CostGroup getCreated()
    {
        return new CostGroup(GROUP_ID+5,"New group");
    }

    public static final CostGroup getUpdate()
    {
        return new CostGroup(GROUP_ID+0, "UpdateFood");
    }
}
