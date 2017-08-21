package de.shokhor.costs;

import de.shokhor.costs.matcher.ModelMatcher;
import de.shokhor.costs.model.Role;
import de.shokhor.costs.model.User;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.of;

/**
 * Created by user on 12.07.2017.
 */
public class userTestData {

    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected==actual||
                    (Objects.equals(expected.getPassword(),actual.getPassword())
                            && Objects.equals(expected.getAge(), actual.getAge())
                            && Objects.equals(expected.getEmail(),actual.getEmail())
                            && Objects.equals(expected.getId(),actual.getId())
                            && Objects.equals(expected.getFirstName(),actual.getFirstName())
                            && Objects.equals(expected.getSirname(),actual.getSirname())
                    )
    );

    public static final int USER_ID = 1;

    public static final User ADMIN = new User(USER_ID +0, "Ilya", "Shokhor", "shohor@mail.ru", 34, "Shish1983", of(2017, Month.JANUARY, 1, 10, 0), Role.ROLE_USER,Role.ROLE_ADMIN);
    public static final User USER = new User(USER_ID +1, "Natalia", "Kozlovskaya", "natashik84@list.ru", 33, "Russia1984", of(2017, Month.JANUARY, 1, 10, 0),Role.ROLE_USER);

    public static final List<User> USERS = Arrays.asList(USER, ADMIN);

    public static final User getCreated()
    {
        return new User(USER_ID+2, "Ivan", "Ivanov", "Ivanov@mail.com", 25, "Ivanov1234", of(2017, Month.JANUARY, 1, 10, 0),Role.ROLE_USER);
    }

    public static final User getUpdate()
    {
        return new User(USER_ID +0, "Ilya_New", "Shokhor", "shohor@mail.ru", 34, "Shish1983", of(2017, Month.JANUARY, 1, 10, 0), Role.ROLE_USER);
    }
}
