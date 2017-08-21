package de.shokhor.costs.web.cost;

import de.shokhor.costs.model.Cost;
import de.shokhor.costs.model.Group;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/cost")
public class AjaxCostController extends AbstractCostController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cost> getAll() {
        return super.getAll();
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void updateOrCreate(@RequestParam("id") Integer id,
                               @RequestParam("groupId") int groupId,
                               @RequestParam("price") double price,
                               @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                               @RequestParam("description") String description) {
        Cost cost = new Cost(id, price, date, description);
        if (cost.isNew()) {
            super.create(cost, groupId);
        } else {
            super.update(cost, id, groupId);
        }
    }
}
