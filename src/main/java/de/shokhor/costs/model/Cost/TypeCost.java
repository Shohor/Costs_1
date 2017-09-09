package de.shokhor.costs.model.Cost;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.shokhor.costs.model.BaseEntity;
import de.shokhor.costs.model.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by user on 08.07.2017.
 */
@NamedQueries({
        @NamedQuery(name = TypeCost.DELETE, query = "DELETE FROM TypeCost g WHERE g.id=:id AND g.user.id=:userId"),
        @NamedQuery(name = TypeCost.ALL, query = "SELECT g FROM TypeCost g WHERE g.user.id=:userId")
})

@Entity
@Table(name = "type_cost")
public class TypeCost extends BaseEntity {

    public static final String DELETE = "group.delete";
    public static final String ALL = "group.getAll";

    @Column(name = "type")
    @NotNull
    private String type;

    @OneToMany (mappedBy = "typeCost")
    @JsonIgnore
    private List<Cost> costs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public TypeCost() {
    }

    public TypeCost(Integer id, String type) {
        super(id);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String group) {
        this.type = group;
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
