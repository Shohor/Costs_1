package de.shokhor.costs.web.group;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.model.Cost;
import de.shokhor.costs.model.Group;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
@RequestMapping(value = "/groups")
public class GroupController extends AbstractGroupController {

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request)
    {
        super.delete(getId(request));
        return "redirect:/costs";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Model model)
    {
        model.addAttribute("groupList", super.getAll());
        model.addAttribute("group",super.get(getId(request)));
        return "group";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(HttpServletRequest request, Model model)
    {
        model.addAttribute("group", new Group(null,""));
        return "group";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateOrCreate(HttpServletRequest request) {
        String id = request.getParameter("id");
        Group userGroup = new Group(id.isEmpty() ? null : Integer.valueOf(id),
                request.getParameter("group"));

        if (userGroup.isNew()) {
            super.create(userGroup);
        } else {
            super.update(userGroup, userGroup.getId());
        }
        return "redirect:/costs";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
