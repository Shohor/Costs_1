package de.shokhor.costs.model.Income;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.shokhor.costs.model.BaseEntity;
import de.shokhor.costs.model.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = TypeIncome.GET_ALL, query = "SELECT t FROM TypeIncome t WHERE t.user.id=:userId"),
        @NamedQuery(name = TypeIncome.GET, query = "SELECT t FROM TypeIncome t WHERE t.user.id=:userId AND t.id=:id"),
        @NamedQuery(name = TypeIncome.DELETE, query = "DELETE FROM TypeIncome t WHERE t.user.id=:userId AND t.id=:id")
})

@Entity
@Table(name = "type_income")
public class TypeIncome extends BaseEntity {

    public static final String GET_ALL = "TypeIncome.getall";
    public static final String GET = "TypeIncome.get";
    public static final String DELETE = "TypeIncome.delete";

    @Column(name = "type")
    @NotNull
    private String type;

    @Column(name ="description")
    private String description;

    @OneToMany(mappedBy = "typeIncome")
    @JsonIgnore
    private List<Income> incomes;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public TypeIncome() {
    }

    public TypeIncome(Integer id, String type, String description, List<Income> incomes) {
        super(id);
        this.type = type;
        this.description = description;
        this.incomes = incomes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
