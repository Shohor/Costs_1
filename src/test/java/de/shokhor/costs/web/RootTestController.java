package de.shokhor.costs.web;

import org.hamcrest.Matchers;
import org.junit.Test;

import static de.shokhor.costs.userTestData.USER;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class RootTestController extends AbstractControllerTest {

    @Test
    public void testUsers() throws Exception
    {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("userList", hasSize(2)))
                .andExpect(model().attribute("userList", hasItem(
                        allOf(
                                hasProperty("id", Matchers.is(2)),
                                hasProperty("firstName", Matchers.is(USER.getFirstName()))
                        )
                )));
    }
}
