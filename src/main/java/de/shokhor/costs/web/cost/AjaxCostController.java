package de.shokhor.costs.web.cost;

import de.shokhor.costs.model.Cost;
import de.shokhor.costs.to.CostTo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ajax/cost")
public class AjaxCostController extends AbstractCostController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cost> getAll() {
        List<Cost> costs = super.getAll();
        return costs;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cost get (@PathVariable("id") int id)
    {
        return super.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public ResponseEntity<String> updateOrCreate(@Valid CostTo costTo, BindingResult result){
        if (result.hasErrors())
        {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (costTo.isNew()) {
            super.create(costTo);
        } else {
            super.update(costTo);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cost> filter(@RequestParam(value = "groupId", required = false) Integer groupId,
                             @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                             @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
    {
        if (startDate==null)
        {
            startDate=super.minDate();
        }
        if (endDate==null)
        {
            endDate=super.maxDate();
        }
        if (groupId==null)
        {
            return super.getBetween(startDate,endDate);
        }

        return super.filter(groupId, startDate, endDate);
    }

    @PostMapping(value = "/bygroup", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cost> getAllByGroup(@RequestParam("groupId") String groupId)
    {
        int grId= Integer.parseInt(groupId);
        return super.getAllByGroup(grId);
    }
}
