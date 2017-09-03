package de.shokhor.costs.web.group;

import de.shokhor.costs.model.CostGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ajax/groups")
public class AjaxGroupController extends AbstractGroupController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CostGroup> getAll() {
        List<CostGroup> costGroupList = super.getAll();
        return costGroupList;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CostGroup get (@PathVariable("id") int id)
    {
        return super.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public ResponseEntity<String> updateOrCreate(@Valid CostGroup costGroup, BindingResult result){
        if (result.hasErrors())
        {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (costGroup.isNew()) {
            super.create(costGroup);
        } else {
            super.update(costGroup);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
