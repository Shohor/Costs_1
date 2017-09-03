package de.shokhor.costs.web.user;

import de.shokhor.costs.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = RestUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestUserController extends AbstractUserController {

    static final String REST_URL = "/rest/admin/users";

    @GetMapping("/{id}")
    public User get (@PathVariable("id") int id)
    {
        return super.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable("id") int id)
    {
        super.delete(id);
    }

    @GetMapping
    public List<User> getAll ()
    {
        return super.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user, @PathVariable("id") int id)
    {
        return super.update(user,id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create (@RequestBody User user)
    {
        return super.create(user);
    }

    @GetMapping ("/byemail")
    public User getByEmail (@RequestParam("email") String email)
    {
        return super.getByEmail(email);
    }
}
