package de.shokhor.costs.model;


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
    @NamedQuery(name = Cost.BY_GROUP, query = "SELECT c FROM Cost c WHERE c.user.id=:userId AND c.costGroup.id=:groupId"),
    @NamedQuery(name = Cost.FILTER, query = "SELECT c FROM  Cost c WHERE c.user.id=:userId AND c.costGroup.id =:groupId " +
            "AND c.date BETWEEN :startDate AND :endDate"),
    @NamedQuery(name = Cost.MIN_DATE, query = "SELECT min(c.date) FROM Cost c WHERE c.user.id=:userId"),
    @NamedQuery(name = Cost.MAX_DATE, query = "SELECT max (c.date) FROM Cost c WHERE c.user.id=:userId"),
    @NamedQuery(name = Cost.GET_BETWEEN, query = "SELECT c FROM  Cost c WHERE c.user.id=:userId AND c.date BETWEEN :startDate AND :endDate")
})
@Entity
@Table(name = "cost")
public class Cost extends BaseEntity {

    public static final String DELETE = "Cost.delete";
    public static final String ALL_SORTED = "Cost.getAll";
    public static final String BY_GROUP = "Cost.getByGroup";
    public static final String FILTER="Cost.filter";
    public static final String MIN_DATE = "Cost.min_date";
    public static final String MAX_DATE = "Cost.max_date";
    public static final String GET_BETWEEN="Cost.get_between";


    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private CostGroup costGroup;

    public Cost() {
    }

    public Cost(Integer id, double price, LocalDate date, String description, User user, CostGroup costGroup) {
        super(id);
        this.price = price;
        this.date = date;
        this.user = user;
        this.costGroup = costGroup;
        this.description = description;
    }

    public Cost(Integer id, double price, LocalDate date, String description) {
        super(id);
        this.price = price;
        this.date = date;
        this.description = description;
    }

    public Cost(Integer id, double price, LocalDate date) {
        super(id);
        this.price = price;
        this.date = date;
    }

    public Cost(LocalDate localDateTime) {
        this.id=null;
        this.date=localDateTime;
    }

    public Cost(Integer id, LocalDate date, int price, String description) {
        super(id);
        this.date=date;
        this.price=price;
        this.description=description;
    }

    public Cost(Integer id, double price, String description) {
        this.id=id;
        this.price=price;
        this.description=description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public CostGroup getCostGroup() {
        return costGroup;
    }

    public void setCostGroup(CostGroup costGroup) {
        this.costGroup = costGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
