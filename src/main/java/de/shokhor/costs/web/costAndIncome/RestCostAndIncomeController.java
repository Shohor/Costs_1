package de.shokhor.costs.web.costAndIncome;


import de.shokhor.costs.model.Cost.Cost;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RestCostAndIncomeController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestCostAndIncomeController extends AbstractCostAndIncomeController
{
    static final String REST_URL = "/rest/user/costs";

    @GetMapping("/{id}")
    public Cost get (@PathVariable("id") int id)
    {
        return super.getCost(id);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable("id") int id)
    {
        super.costDelete(id);
    }

    @GetMapping
    public List<Cost> getAll ()
    {
        return super.getAllCosts();
    }

    /*@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cost update(@RequestBody Cost costAndIncome, @RequestParam (value = "groupId") int groupId)
    {
        return super.update(costAndIncome,groupId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cost create (@RequestBody Cost costAndIncome, @RequestParam (value = "groupId") int groupId)
    {
        return super.create(costAndIncome, groupId);
    }

    @GetMapping("/bygroup")
    public List<Cost> getAllByGroup (@RequestParam (value = "groupId") int groupId)
    {
        return super.getAllByGroup(groupId);
    }*/




}
