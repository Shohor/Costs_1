package de.shokhor.costs;

import de.shokhor.costs.matcher.ModelMatcher;
import de.shokhor.costs.model.User.Role;
import de.shokhor.costs.model.User.User;
import de.shokhor.costs.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.of;

/**
 * Created by user on 12.07.2017.
 */
public class userTestData {

    private static final Logger LOG = LoggerFactory.getLogger(userTestData.class);

    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected==actual||
                    (comparePassword(expected.getPassword(),actual.getPassword())
                            && Objects.equals(expected.getAge(), actual.getAge())
                            && Objects.equals(expected.getEmail(),actual.getEmail())
                            && Objects.equals(expected.getId(),actual.getId())
                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
                            && Objects.equals(expected.getFirstName(),actual.getFirstName())
                            && Objects.equals(expected.getSirname(),actual.getSirname())
                    )
    );

    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }

    public static final int USER_ID = 1;

    public static final User USER_TEST = new User(USER_ID+2, "User_firstname", "User_sirname", "aaaa@sss.dd", 25, "1234567", true, Role.ROLE_USER);
    public static final User ADMIN = new User(USER_ID +0, "Ilya", "Shokhor", "shohor@mail.ru", 34, "$2a$10$nLAIv6MfFCp2g1Y9iW.z0ubvrMzI2L0Sd.zimVj/REx2kQe1/A2xK", of(2017, Month.JANUARY, 1, 10, 0), true, Role.ROLE_USER,Role.ROLE_ADMIN);
    public static final User USER = new User(USER_ID +1, "Natalia", "Kozlovskaya", "natashik84@list.ru", 33, "$2a$10$Q73bLAGTC8935gUsJI/RTeens/s9TokTGTWl6Ih3q3xQaGaVMHvUi", of(2017, Month.JANUARY, 1, 10, 0), true, Role.ROLE_USER);

    public static final List<User> USERS = Arrays.asList(USER, ADMIN);

    public static final User getCreated()
    {
        return new User(USER_ID+2, "User_firstname", "User_sirname", "aaaa@sss.dd", 25, "1234567",of(2017, Month.JANUARY, 1, 10, 0), true, Role.ROLE_USER);
    }

    public static final User getUpdate()
    {
        return new User(USER_ID +0, "Ilya_New", "Shokhor", "shohor@mail.ru", 34, "Shish1983", of(2017, Month.JANUARY, 1, 10, 0), true,  Role.ROLE_USER);
    }
}
