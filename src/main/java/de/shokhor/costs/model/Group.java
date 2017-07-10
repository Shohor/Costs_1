package de.shokhor.costs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by user on 08.07.2017.
 */
@Entity
@Table(name = "groups")
public class Group extends BaseEntity {

    @Column(name = "Group")
    @NotNull
    private String group;

    @OneToMany (mappedBy = "group")
    private List<Cost> costs;

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
}
