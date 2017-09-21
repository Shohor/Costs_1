package de.shokhor.costs.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.model.User.User;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = CashAccountsAndCards.GET_ALL, query = "SELECT c FROM CashAccountsAndCards c WHERE c.user.id=:userId"),
        @NamedQuery(name = CashAccountsAndCards.GET, query = "SELECT c FROM CashAccountsAndCards c WHERE c.user.id=:userId AND c.id=:id"),
        @NamedQuery(name = CashAccountsAndCards.DELETE, query = "DELETE FROM CashAccountsAndCards c WHERE c.user.id=:userId AND c.id=:id"),
        @NamedQuery(name = CashAccountsAndCards.SUMM_INCOME, query = "SELECT SUM(i.amount) FROM CashAccountsAndCards c LEFT JOIN Income i ON i.cashAccountsAndCards.id=c.id WHERE c.user.id=:userId " +
                "AND c.id=:id"),
        @NamedQuery(name = CashAccountsAndCards.SUMM_COST, query = "SELECT SUM(c.amount) FROM CashAccountsAndCards a LEFT JOIN Cost c ON c.cashAccountsAndCards.id=a.id WHERE a.user.id=:userId " +
                "AND a.id=:id")
})

@Entity
@Table(name = "cash_accounts_and_cards")
public class CashAccountsAndCards extends BaseEntity {

    public static final String GET_ALL = "CashAccountsAndCards.getall";
    public static final String GET = "CashAccountsAndCards.get";
    public static final String DELETE = "CashAccountsAndCards.delete";
    public static final String SUMM_INCOME = "CashAccountsAndCards.SummIncome";
    public static final String SUMM_COST = "CashAccountsAndCards.SummCost";

    @Transient
    private double amount;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "type_cash_accounts_and_cards", joinColumns = @JoinColumn(name = "cash_accounts_and_cards_id"))
    @Column(name = "type")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<TypeCashAccountsAndCards> type;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "cashAccountsAndCards")
    @JsonIgnore
    private List<Income> income;

    @OneToMany(mappedBy = "cashAccountsAndCards")
    @JsonIgnore
    private List<Cost> costs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public CashAccountsAndCards() {
    }

    public CashAccountsAndCards(Integer id, int amount, String description, TypeCashAccountsAndCards type, TypeCashAccountsAndCards... types) {
        super(id);
        this.amount = amount;
        EnumSet.of(type,types);
        this.description = description;
    }

    public CashAccountsAndCards(Integer id, int amount, Set<TypeCashAccountsAndCards> type, String description, List<Income> income, List<Cost> costs, User user) {
        super(id);
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.income = income;
        this.costs = costs;
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Set<TypeCashAccountsAndCards> getType() {
        return type;
    }

    public void setType(Set<TypeCashAccountsAndCards> type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Income> getIncome() {
        return income;
    }

    public void setIncome(List<Income> income) {
        this.income = income;
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
