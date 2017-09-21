package de.shokhor.costs.model.Income;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.shokhor.costs.model.BaseEntity;
import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.model.Periodicity;
import de.shokhor.costs.model.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Income.GET_ALL, query = "SELECT i FROM Income i WHERE i.user.id=:userId"),
        @NamedQuery(name = Income.GET, query = "SELECT i FROM Income i WHERE i.user.id=:userId AND i.id=:id"),
        @NamedQuery(name = Income.DELETE, query = "DELETE FROM Income i WHERE i.user.id=:userId AND i.id=:id"),
        @NamedQuery(name = Income.BY_GROUP, query = "SELECT i FROM Income i WHERE i.user.id=:userId AND i.typeIncome.id=:typeIncomeId"),
        @NamedQuery(name = Income.FILTER, query = "SELECT i FROM  Income i WHERE i.user.id=:userId AND i.typeIncome.id =:typeIncomeId " +
                "AND i.date BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = Income.MIN_DATE, query = "SELECT min(i.date) FROM Income i WHERE i.user.id=:userId"),
        @NamedQuery(name = Income.MAX_DATE, query = "SELECT max (i.date) FROM Income i WHERE i.user.id=:userId"),
        @NamedQuery(name = Income.GET_BETWEEN, query = "SELECT i FROM Income i WHERE i.user.id=:userId AND i.date BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = Income.GET_BETWEEN_BY_TYPE, query = "SELECT i FROM Income i WHERE i.user.id=:userId AND i.typeIncome.id=:typeIncomeId " +
                "AND i.date BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = Income.GET_BETWEEN_BY_CARDS, query = "SELECT i FROM Income i WHERE i.user.id=:userId AND i.cashAccountsAndCards.id=:cashAccountsAndCardsId " +
                "AND i.date BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = Income.GET_BETWEEN_BY_TYPE_CARDS, query = "SELECT i FROM Income i WHERE i.user.id=:userId AND i.typeIncome.id=:typeIncomeId " +
                "AND i.cashAccountsAndCards.id=:cashAccountsAndCardsId AND i.date BETWEEN :startDate AND :endDate")
})

@Entity
@Table(name = "income")
public class Income extends BaseEntity
{
    public static final String GET_ALL = "Income.getall";
    public static final String GET = "Income.get";
    public static final String DELETE = "Income.delete";
    public static final String BY_GROUP = "Income.getByGroup";
    public static final String FILTER="Income.filter";
    public static final String MIN_DATE = "Income.min_date";
    public static final String MAX_DATE = "Income.max_date";
    public static final String GET_BETWEEN="Income.get_between";
    public static final String GET_BETWEEN_BY_TYPE="Income.getBetweenByType";
    public static final String GET_BETWEEN_BY_CARDS="Income.getBetweenByCards";
    public static final String GET_BETWEEN_BY_TYPE_CARDS="Income.getBetweenByTypeAndCards";

    @Column(name = "amount")
    @NotNull
    private double amount;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_income_id")
    private TypeIncome typeIncome;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cash_accounts_and_cards_id")
    private CashAccountsAndCards cashAccountsAndCards;

    /*@Enumerated(EnumType.STRING)
    @CollectionTable(name = "periodicity", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "periodicity")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Periodicity> periodicity;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Income() {
    }

    public Income(Integer id, double amount, LocalDate date, String description) {
        super(id);
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public Income(Integer id, double amount, TypeIncome typeIncome, String description, CashAccountsAndCards cashAccountsAndCards, User user) {
        super(id);
        this.amount = amount;
        this.typeIncome = typeIncome;
        this.description = description;
        this.cashAccountsAndCards = cashAccountsAndCards;
        /*this.periodicity = periodicity;*/
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TypeIncome getTypeIncome() {
        return typeIncome;
    }

    public void setTypeIncome(TypeIncome typeIncome) {
        this.typeIncome = typeIncome;
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

    public void setCashAccountsAndCards(CashAccountsAndCards cashMoneyAndCards) {
        this.cashAccountsAndCards = cashMoneyAndCards;
    }

    /*public Set<Periodicity> getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Set<Periodicity> periodicity) {
        this.periodicity = periodicity;
    }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
