package de.shokhor.costs.web.cashAccountsAndCards;

import de.shokhor.costs.model.CashAccountsAndCards;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ajax/accounts")
public class AjaxAccountsController extends AbstractAccountsController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CashAccountsAndCards> getAll()
    {
        List<CashAccountsAndCards> list = super.getAll();
        for (CashAccountsAndCards c:list)
        {
            c.setAmount(super.summIncome(c.getId())-super.summCost(c.getId()));
        }
        return list;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CashAccountsAndCards get (@PathVariable("id") int id)
    {
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> updateOrCreate(@Valid CashAccountsAndCards cashAccountsAndCards, BindingResult result){
        if (result.hasErrors())
        {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (cashAccountsAndCards.isNew()) {
            super.create(cashAccountsAndCards);
        } else {
            super.update(cashAccountsAndCards);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}
