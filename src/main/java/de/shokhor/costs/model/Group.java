package de.shokhor.costs.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by user on 08.07.2017.
 */
@NamedQueries({
        @NamedQuery(name = Group.DELETE, query = "DELETE FROM Group g WHERE g.id=:id AND g.user.id=:userId"),
        @NamedQuery(name = Group.ALL, query = "SELECT g FROM Group g WHERE g.user.id=:userId")
})

@Entity
@Table(name = "groups")
public class Group extends BaseEntity {

    public static final String DELETE = "group.delete";
    public static final String ALL = "group.getAll";

    @Column(name = "group_cost")
    @NotNull
    private String group;

    @OneToMany (mappedBy = "group")
    private List<Cost> costs;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Group() {
    }

    public Group(int id, String group) {
        super(id);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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
