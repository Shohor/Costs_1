package de.shokhor.costs;

import de.shokhor.costs.matcher.ModelMatcher;
import de.shokhor.costs.model.Group;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 12.07.2017.
 */
public class groupTestData {

    public static final ModelMatcher<Group> MATCHER = ModelMatcher.of(Group.class);

    public static final int GROUP_ID = 1;

    public static final Group GROUP1 = new Group(GROUP_ID +0, "Food");
    public static final Group GROUP2 = new Group(GROUP_ID +1, "Children");
    public static final Group GROUP3 = new Group(GROUP_ID +2, "House");
    public static final Group GROUP4 = new Group(GROUP_ID +3, "Presents");
    public static final Group GROUP5 = new Group(GROUP_ID +4, "Food");

    public static final List<Group> GROUPS = Arrays.asList(GROUP1,GROUP2,GROUP4);

    public static final Group getCreated()
    {
        return new Group(GROUP_ID+5,"New group");
    }

    public static final Group getUpdate()
    {
        return new Group(GROUP_ID+0, "UpdateFood");
    }
}
