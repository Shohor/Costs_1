package de.shokhor.costs.web;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.service.CostService;
import de.shokhor.costs.service.GroupService;
import de.shokhor.costs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private CostService costService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String root ()
    {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users (Model model)
    {
        model.addAttribute("userList", userService.getAll());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUsers(HttpServletRequest httpRequest)
    {
       AuthorizedUser.setId(Integer.valueOf(httpRequest.getParameter("userId")));
       return "redirect:costs";
    }

    @RequestMapping(value = "/costs", method = RequestMethod.GET)
    public String costs (Model model)
    {
        model.addAttribute("costList", costService.getAll(AuthorizedUser.id()));
        model.addAttribute("groupList", groupService.getAll(AuthorizedUser.id()));
        return "costs";
    }
}
