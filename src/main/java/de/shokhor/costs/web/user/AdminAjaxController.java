package de.shokhor.costs.web.user;

import de.shokhor.costs.AuthorizedUser;
import de.shokhor.costs.model.Role;
import de.shokhor.costs.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get (@PathVariable("id") int id)
    {
        return super.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(@Valid User user, BindingResult result) {
        if (result.hasErrors())
        {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        /*User user = new User(id, name, sirName, email, age, password, Role.ROLE_USER);*/
        if (user.isNew()) {
            user.setRegistred(LocalDateTime.now());
            user.setRole(EnumSet.of(Role.ROLE_USER));
            super.create(user);
        } else {
            user.setRegistred(AuthorizedUser.get().getUser().getRegistred());
            user.setRole(AuthorizedUser.get().getUser().getRole());
            super.update(user, user.getId());
            AuthorizedUser.get().update(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
