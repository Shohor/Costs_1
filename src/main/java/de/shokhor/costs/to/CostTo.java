package de.shokhor.costs.to;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CostTo {

    @NotNull
    private Integer id;

    @NotNull
    private int typeId;

    @NotNull
    private double price;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotNull
    private int cashAccountsAndCardsId;

    private String description;

    public CostTo() {
    }

    public CostTo(Integer id, int typeId, LocalDate date, double price, int cashAccountsAndCardsId, String description) {
        this.id = id;
        this.typeId = typeId;
        this.price = price;
        this.description = description;
        this.date = date;
        this.cashAccountsAndCardsId = cashAccountsAndCardsId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
