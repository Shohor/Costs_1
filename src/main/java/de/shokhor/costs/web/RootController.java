package de.shokhor.costs.web;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.model.User;
import de.shokhor.costs.service.CostService;
import de.shokhor.costs.service.GroupService;
import de.shokhor.costs.service.UserService;
import de.shokhor.costs.web.user.AbstractUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RootController extends AbstractUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CostService costService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public String root ()
    {
        return "redirect:costs";
    }

    @GetMapping("/users")
    public String users ()
    {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

    /*@PostMapping("/users")
    public String setUsers(HttpServletRequest httpRequest)
    {
       AuthorizedUser.setId(Integer.valueOf(httpRequest.getParameter("userId")));
       return "redirect:costs";
    }*/

    @GetMapping("/groups")
    public String groups ()
    {
        return "groups";
    }

    @GetMapping("/costs")
    public String costs (Model model)
    {
        model.addAttribute("costList", costService.getAll(AuthorizedUser.id()));
        model.addAttribute("groupList", groupService.getAll(AuthorizedUser.id()));
        return "costs";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(User user, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "profile";
        } else {
            int id= AuthorizedUser.id();
            user.setRegistred(AuthorizedUser.get().getUser().getRegistred());
            user.setRole(AuthorizedUser.get().getUser().getRole());
            super.update(user, id);
            AuthorizedUser.get().update(user);
            status.setComplete();
            return "redirect:meals";
        }
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(User user, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        } else {
            super.create(user);
            status.setComplete();
            return "redirect:login?message=app.registered";
        }
    }


}
