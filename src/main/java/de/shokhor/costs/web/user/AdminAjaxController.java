package de.shokhor.costs.web.user;

import de.shokhor.costs.model.Role;
import de.shokhor.costs.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("firstName") String name,
                               @RequestParam("sirname") String sirName,
                               @RequestParam("age") int age,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {

        User user = new User(id, name, sirName, email, age, password, Role.ROLE_USER);
        if (user.isNew()) {
            super.create(user);
        } else {
            super.update(user, id);
        }
    }
}
