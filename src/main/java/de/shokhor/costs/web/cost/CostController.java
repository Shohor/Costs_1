package de.shokhor.costs.web.cost;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.model.Cost;
import de.shokhor.costs.model.Group;
import de.shokhor.costs.service.CostService;
import de.shokhor.costs.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Created by user on 18.07.2017.
 */
@Controller
@RequestMapping(value = "/costs")
public class CostController extends AbstractCostController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request)
    {
        super.delete(getId(request));
        return "redirect:/costs";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Model model)
    {
        model.addAttribute("cost", super.get(getId(request)));
        model.addAttribute("groupList", groupService.getAll(super.get(getId(request)).getUser().getId()));
        return "cost";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(HttpServletRequest request, Model model)
    {
        model.addAttribute("cost", new Cost(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
        model.addAttribute("groupList", groupService.getAll(AuthorizedUser.id()));
        return "cost";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateOrCreate(HttpServletRequest request) {
        String id = request.getParameter("id");
        int groupId = Integer.valueOf(request.getParameter("groupId"));
        Cost userCost = new Cost(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                Integer.valueOf(request.getParameter("price")),
                request.getParameter("description"));

        if (userCost.isNew()) {
            super.create(userCost, groupId);
        } else {
            super.update(userCost, userCost.getId(), groupId);
        }
        return "redirect:/costs";
    }

    @RequestMapping(value = "/bygroup", method = RequestMethod.POST)
    public String getAllByGroup(HttpServletRequest request)
    {
        int groupId = Integer.valueOf(request.getParameter("groupId"));
        super.getAllByGroup(groupId);
        return "redirect:/costs";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

}
