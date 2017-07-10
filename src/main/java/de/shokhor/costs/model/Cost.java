package de.shokhor.costs.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by user on 08.07.2017.
 */
@Entity
@Table(name = "costs")
public class Cost extends BaseEntity {

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "Price")
    @NotNull
    private double price;

    @Column(name = "Date")
    @NotNull
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "User_idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Groups_idGroups")
    private Group group;

    public Cost() {
    }

    public Cost(int id, String name, double price, LocalDateTime date, User user, Group group) {
        super(id);
        this.name = name;
        this.price = price;
        this.date = date;
        this.user = user;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
