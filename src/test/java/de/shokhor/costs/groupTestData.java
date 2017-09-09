package de.shokhor.costs;

import de.shokhor.costs.matcher.ModelMatcher;
import de.shokhor.costs.model.Cost.TypeCost;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 12.07.2017.
 */
public class groupTestData {

    public static final ModelMatcher<TypeCost> MATCHER = ModelMatcher.of(TypeCost.class);

    public static final int GROUP_ID = 1;

    public static final TypeCost COST_GROUP_1 = new TypeCost(GROUP_ID +0, "Food");
    public static final TypeCost COST_GROUP_2 = new TypeCost(GROUP_ID +1, "Children");
    public static final TypeCost COST_GROUP_3 = new TypeCost(GROUP_ID +2, "House");
    public static final TypeCost COST_GROUP_4 = new TypeCost(GROUP_ID +3, "Presents");
    public static final TypeCost COST_GROUP_5 = new TypeCost(GROUP_ID +4, "Food");

    public static final List<TypeCost> COST_GROUPS = Arrays.asList(COST_GROUP_1, COST_GROUP_2, COST_GROUP_4);

    public static final TypeCost getCreated()
    {
        return new TypeCost(GROUP_ID+5,"New group");
    }

    public static final TypeCost getUpdate()
    {
        return new TypeCost(GROUP_ID+0, "UpdateFood");
    }
}
