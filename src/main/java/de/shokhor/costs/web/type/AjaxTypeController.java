package de.shokhor.costs.web.type;

import de.shokhor.costs.model.Cost.TypeCost;
import de.shokhor.costs.model.Income.TypeIncome;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ajax/types")
public class AjaxTypeController extends AbstractTypeController {

    @GetMapping(value = "/cost",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TypeCost> getAllTypeCost() {
        List<TypeCost> typeCostList = super.getAllTypeCost();
        return typeCostList;
    }

    @GetMapping(value = "/income",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TypeIncome> getAllTypeIncome() {
        List<TypeIncome> typeIncomeList = super.getAllTypeIncome();
        return typeIncomeList;
    }

    @GetMapping(value = "/cost/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TypeCost getCost (@PathVariable("id") int id)
    {
        return super.getCost(id);
    }

    @GetMapping(value = "/income/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TypeIncome getIncome (@PathVariable("id") int id)
    {
        return super.getIncome(id);
    }

    @DeleteMapping(value = "/cost/{id}")
    public void deleteCost(@PathVariable("id") int id) {
        super.deleteCost(id);
    }

    @DeleteMapping(value = "/income/{id}")
    public void deleteIncome(@PathVariable("id") int id) {
        super.deleteIncome(id);
    }

    @PostMapping("/cost")
    public ResponseEntity<String> updateOrCreateCost(@Valid TypeCost typeCost, BindingResult result){
        if (result.hasErrors())
        {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (typeCost.isNew()) {
            super.createCost(typeCost);
        } else {
            super.updateCost(typeCost);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/income")
    public ResponseEntity<String> updateOrCreateIncome(@Valid TypeIncome typeIncome, BindingResult result){
        if (result.hasErrors())
        {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (typeIncome.isNew()) {
            super.createIncome(typeIncome);
        } else {
            super.updateIncome(typeIncome);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

