package de.shokhor.costs.web.cost;


import de.shokhor.costs.model.Cost;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RestCostController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestCostController extends AbstractCostController
{
    static final String REST_URL = "/rest/user/costs";

    @GetMapping("/{id}")
    public Cost get (@PathVariable("id") int id)
    {
        return super.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable("id") int id)
    {
        super.delete(id);
    }

    @GetMapping
    public List<Cost> getAll ()
    {
        return super.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cost update(@RequestBody Cost cost, @PathVariable("id") int id, @RequestParam (value = "groupId") int groupId)
    {
        return super.update(cost,id,groupId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cost create (@RequestBody Cost cost, @RequestParam (value = "groupId") int groupId)
    {
        return super.create(cost, groupId);
    }

    @GetMapping("/bygroup")
    public List<Cost> getAllByGroup (@RequestParam (value = "groupId") int groupId)
    {
        return super.getAllByGroup(groupId);
    }




}
