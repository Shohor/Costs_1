package de.shokhor.costs.model;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by user on 08.07.2017.
 */
@NamedQueries({
    @NamedQuery(name = Cost.DELETE, query = "DELETE FROM Cost c WHERE c.id=:id AND c.user.id=:userId"),
    @NamedQuery(name = Cost.ALL_SORTED, query = "SELECT c FROM Cost c WHERE c.user.id=:userId ORDER BY c.date"),
    @NamedQuery(name = Cost.BY_GROUP, query = "SELECT c FROM Cost c WHERE c.user.id=:userId AND c.group.id=:groupId")
})
@Entity
@Table(name = "cost")
public class Cost extends BaseEntity {

    public static final String DELETE = "Cost.delete";
    public static final String ALL_SORTED = "Cost.getAll";
    public static final String BY_GROUP = "Cost.getByGroup";


    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "date")
    @NotNull
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    public Cost() {
    }

    public Cost(int id, double price, LocalDateTime date, String description, User user, Group group) {
        super(id);
        this.price = price;
        this.date = date;
        this.user = user;
        this.group = group;
        this.description = description;
    }

    public Cost(Integer id, double price, LocalDateTime date, String description) {
        super(id);
        this.price = price;
        this.date = date;
        this.description = description;
    }

    public Cost(Integer id, double price, LocalDateTime date) {
        super(id);
        this.price = price;
        this.date = date;
    }

    public Cost(LocalDateTime localDateTime) {
        this.id=null;
        this.date=localDateTime;
    }

    public Cost(Integer id, LocalDateTime date, int price, String description) {
        super(id);
        this.date=date;
        this.price=price;
        this.description=description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
