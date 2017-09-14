package de.shokhor.costs.web.costAndIncome;

import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.to.CostTo;
import de.shokhor.costs.to.IncomeAndCostTo;
import de.shokhor.costs.to.IncomeTo;
import de.shokhor.costs.util.CostUtil;
import de.shokhor.costs.util.IncomeAndCostUtil;
import de.shokhor.costs.util.IncomeUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ajax/cost")
public class AjaxCostAndIncomeController extends AbstractCostAndIncomeController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IncomeAndCostTo> getAll() {
        return IncomeAndCostUtil.transferIncomeAndCost(super.getAllCosts(), super.getAllIncomes());
    }

    @GetMapping(value = "/costs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CostTo> getAllCostsTo()
    {
        return CostUtil.createCostTofromCost(super.getAllCosts());
    }

    @GetMapping(value = "/incomes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IncomeTo> getAllIncomesTo()
    {
        List<IncomeTo> list = IncomeUtil.createCostTofromCost(super.getAllIncomes());
        return list;
    }

    @PostMapping("/saveIncome")
    public ResponseEntity<String> updateOrCreateIncome(@Valid IncomeTo incomeTo, BindingResult result){
        if (result.hasErrors())
        {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (incomeTo.isNew()) {
            super.create(incomeTo);
        } else {
            super.update(incomeTo);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/cost/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cost costGet (@PathVariable("id") int id)
    {
        return super.getCost(id);
    }

    @GetMapping(value = "/income/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Income incomeGet (@PathVariable("id") int id)
    {
        return super.getIncome(id);
    }

    @DeleteMapping(value = "/cost/{id}")
    public void deleteCost(@PathVariable("id") int id) {
        super.costDelete(id);
    }

    @DeleteMapping(value = "/income/{id}")
    public void deleteIncome(@PathVariable("id") int id) {
        super.incomeDelete(id);
    }

    @PostMapping("/saveCost")
    public ResponseEntity<String> updateOrCreateCost(@Valid CostTo costTo, BindingResult result){
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
    public List<Cost> filter(@RequestParam(value = "typeIncomeId", required = false) Integer typeIncomeId,
                             @RequestParam(value = "typeCostId", required = false) Integer typeCostId,
                             @RequestParam(value = "cashAccountsAndCardsId", required = false) Integer cashAccountsAndCardsId,
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
        if (cashAccountsAndCardsId==null)
        {
            return super.getBetween(startDate,endDate);
        }

        return super.filter(cashAccountsAndCardsId, startDate, endDate);
    }
}
