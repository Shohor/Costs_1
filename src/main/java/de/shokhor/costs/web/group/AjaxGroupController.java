package de.shokhor.costs.web.group;

import de.shokhor.costs.model.Cost.TypeCost;
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
    public List<TypeCost> getAll() {
        List<TypeCost> typeCostList = super.getAll();
        return typeCostList;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TypeCost get (@PathVariable("id") int id)
    {
        return super.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public ResponseEntity<String> updateOrCreate(@Valid TypeCost typeCost, BindingResult result){
        if (result.hasErrors())
        {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (typeCost.isNew()) {
            super.create(typeCost);
        } else {
            super.update(typeCost);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
