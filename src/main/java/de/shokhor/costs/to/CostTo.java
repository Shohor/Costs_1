package de.shokhor.costs.to;

import de.shokhor.costs.model.Cost.Cost;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CostTo {

    private Integer id;

    @NotNull
    private int typeId;

    private String type;

    private String cashAccountsAndCards;

    @NotNull
    private double amount;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotNull
    private int cashAccountsAndCardsId;

    private String description;

    public CostTo() {
    }

    public CostTo(Integer id, int typeId, LocalDate date, double amount, int cashAccountsAndCardsId, String description) {
        this.id = id;
        this.typeId = typeId;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.cashAccountsAndCardsId = cashAccountsAndCardsId;
    }

    public CostTo(Cost cost) {
        this.id = cost.getId();
        this.type = cost.getTypeCost().getType();
        this.cashAccountsAndCards = cost.getCashAccountsAndCards().getType().toString();
        this.amount = cost.getAmount();
        this.date = cost.getDate();
        this.description = cost.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    public int getCashAccountsAndCardsId() {
        return cashAccountsAndCardsId;
    }

    public void setCashAccountsAndCardsId(int cashAccountsAndCardsId) {
        this.cashAccountsAndCardsId = cashAccountsAndCardsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCashAccountsAndCards() {
        return cashAccountsAndCards;
    }

    public void setCashAccountsAndCards(String cashAccountsAndCards) {
        this.cashAccountsAndCards = cashAccountsAndCards;
    }
}
