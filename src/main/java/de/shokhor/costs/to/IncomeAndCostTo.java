package de.shokhor.costs.to;

import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.model.Income.Income;

import java.time.LocalDate;

public class IncomeAndCostTo
{

    private Integer id;


    private String type;


    private double amount;


    private boolean incomeOrCost;

    private LocalDate date;

    private String cashAccountsAndCards;

    private String description;

    public IncomeAndCostTo() {
    }

    public IncomeAndCostTo(Cost cost)
    {
        this.id= cost.getId();
        this.type = cost.getTypeCost().getType();
        this.amount=cost.getAmount();
        this.incomeOrCost=false;
        this.cashAccountsAndCards=cost.getCashAccountsAndCards().getType().toString();
        this.description=cost.getDescription();
        this.date=cost.getDate();
    }

    public IncomeAndCostTo(Income income)
    {
        this.id= income.getId();
        this.type = income.getTypeIncome().getType();
        this.amount=income.getAmount();
        this.incomeOrCost=true;
        this.cashAccountsAndCards=income.getCashAccountsAndCards().getType().toString();
        this.description=income.getDescription();
        this.date=income.getDate();
    }

    public IncomeAndCostTo(Integer id, String type, LocalDate date, double amount, boolean incomeOrCost, String cashAccountsAndCards, String description) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.cashAccountsAndCards = cashAccountsAndCards;
        this.incomeOrCost = incomeOrCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isNew()
    {
        return (getId()== null);
    }

    public String getCashAccountsAndCards() {
        return cashAccountsAndCards;
    }

    public void setCashAccountsAndCards(String cashAccountsAndCards) {
        this.cashAccountsAndCards = cashAccountsAndCards;
    }

    public boolean isIncomeOrCost() {
        return incomeOrCost;
    }

    public void setIncomeOrCost(boolean incomeOrCost) {
        this.incomeOrCost = incomeOrCost;
    }
}
