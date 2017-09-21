package de.shokhor.costs.model.Cost;


import com.fasterxml.jackson.annotation.JsonIgnore;
import de.shokhor.costs.model.BaseEntity;
import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.model.User.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by user on 08.07.2017.
 */
@NamedQueries({
    @NamedQuery(name = Cost.DELETE, query = "DELETE FROM Cost c WHERE c.id=:id AND c.user.id=:userId"),
    @NamedQuery(name = Cost.ALL_SORTED, query = "SELECT c FROM Cost c WHERE c.user.id=:userId ORDER BY c.date"),
    @NamedQuery(name = Cost.BY_GROUP, query = "SELECT c FROM Cost c WHERE c.user.id=:userId AND c.typeCost.id=:groupId"),
    @NamedQuery(name = Cost.FILTER, query = "SELECT c FROM  Cost c WHERE c.user.id=:userId AND c.typeCost.id =:groupId " +
            "AND c.date BETWEEN :startDate AND :endDate"),
    @NamedQuery(name = Cost.MIN_DATE, query = "SELECT min(c.date) FROM Cost c WHERE c.user.id=:userId"),
    @NamedQuery(name = Cost.MAX_DATE, query = "SELECT max (c.date) FROM Cost c WHERE c.user.id=:userId"),
    @NamedQuery(name = Cost.GET_BETWEEN, query = "SELECT c FROM  Cost c WHERE c.user.id=:userId AND c.date BETWEEN :startDate AND :endDate"),
    @NamedQuery(name = Cost.GET_BETWEEN_BY_TYPE, query = "SELECT c FROM Cost c WHERE c.user.id=:userId AND c.typeCost.id=:typeCostId " +
            "AND c.date BETWEEN :startDate AND :endDate"),
    @NamedQuery(name = Cost.GET_BETWEEN_BY_CARDS, query = "SELECT c FROM Cost c WHERE c.user.id=:userId AND c.cashAccountsAndCards.id=:cashAccountsAndCardsId " +
                "AND c.date BETWEEN :startDate AND :endDate"),
    @NamedQuery(name = Cost.GET_BETWEEN_BY_TYPE_CARDS, query = "SELECT c FROM Cost c WHERE c.user.id=:userId AND c.cashAccountsAndCards.id=:cashAccountsAndCardsId " +
                "AND c.typeCost.id=:typeCostId AND c.date BETWEEN :startDate AND :endDate")
})
@Entity
@Table(name = "cost")
public class Cost extends BaseEntity {

    public static final String DELETE = "Cost.delete";
    public static final String ALL_SORTED = "Cost.getAll";
    public static final String BY_GROUP = "Cost.getByGroup";
    public static final String FILTER="Cost.filter";
    public static final String MIN_DATE = "Cost.minDate";
    public static final String MAX_DATE = "Cost.maxDate";
    public static final String GET_BETWEEN="Cost.getBetween";
    public static final String GET_BETWEEN_BY_TYPE = "Cost.getBetweenByType";
    public static final String GET_BETWEEN_BY_CARDS = "Cost.getBetweenByCards";
    public static final String GET_BETWEEN_BY_TYPE_CARDS = "Cost.getBetweenByTypeAndCards";


    @Column(name = "amount")
    @NotNull
    private double amount;

    @Column(name = "date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private TypeCost typeCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cash_accounts_and_cards_id")
    private CashAccountsAndCards cashAccountsAndCards;

    public Cost() {
    }

    public Cost(Integer id, double amount, LocalDate date, String description, User user, TypeCost typeCost, CashAccountsAndCards cashAccountsAndCards) {
        super(id);
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.user = user;
        this.typeCost = typeCost;
        this.cashAccountsAndCards = cashAccountsAndCards;
    }

    public Cost(Integer id, double amount, LocalDate date, String description) {
        super(id);
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public Cost(Integer id, double amount, LocalDate date) {
        super(id);
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double price) {
        this.amount = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeCost getTypeCost() {
        return typeCost;
    }

    public void setTypeCost(TypeCost typeCost) {
        this.typeCost = typeCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CashAccountsAndCards getCashAccountsAndCards() {
        return cashAccountsAndCards;
    }

    public void setCashAccountsAndCards(CashAccountsAndCards cashAccountsAndCards) {
        this.cashAccountsAndCards = cashAccountsAndCards;
    }
}
