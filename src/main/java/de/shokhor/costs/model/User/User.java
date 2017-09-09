package de.shokhor.costs.model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.shokhor.costs.model.BaseEntity;
import de.shokhor.costs.model.CashAccountsAndCards;
import de.shokhor.costs.model.Cost.Cost;
import de.shokhor.costs.model.Cost.TypeCost;
import de.shokhor.costs.model.Income.Income;
import de.shokhor.costs.model.Income.TypeIncome;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 08.07.2017.
 */
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.sirname"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=:email")
})

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    public static final String DELETE = "User.delete";
    public static final String ALL_SORTED = "User.getAll";
    public static final String BY_EMAIL = "User.getByEmail";

    @Column(name = "firstName")
    @NotNull
    private String firstName;

    @Column(name = "sirname")
    @NotNull
    private String sirname;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "age")
    @NotNull
    private int age;

    @Column(name = "password")
    @NotNull
    @Size(min = 5, max = 64, message = " must between 5 and 64 characters")
    private String password;

    @Column(name = "registred")
    @NotNull
    private LocalDateTime registred;

    @Column(name = "enabled")
    private boolean enabled=true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "Role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Cost> costs;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<TypeCost> typeCosts;

    @OneToMany(mappedBy = "user")
    private List<Income> incomes;

    @OneToMany(mappedBy = "user")
    private List<CashAccountsAndCards> cashAccountsAndCards;

    @OneToMany(mappedBy = "user")
    private List<TypeIncome> typeIncomes;

    public User() {
    }

    public User(Integer id, String firstName, String sirname, String email, int age, String password, boolean enabled, Role role, Role... roles) {
        super(id);
        this.firstName = firstName;
        this.sirname = sirname;
        this.email = email;
        this.age = age;
        this.registred = LocalDateTime.now();
        this.password = password;
        EnumSet.of(role,roles);
        this.enabled=enabled;
    }

    public User(int id, String firstName, String sirname, String email, int age, String password, LocalDateTime registred, boolean enabled, Role role, Role...roles) {
        super(id);
        this.firstName = firstName;
        this.sirname = sirname;
        this.email = email;
        this.age = age;
        this.password = password;
        this.registred = registred;
        EnumSet.of(role,roles);
        this.enabled=enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSirname() {
        return sirname;

    }

    public void setSirname(String sirname) {
        this.sirname = sirname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistred() {
        return registred;
    }

    public void setRegistred(LocalDateTime registred) {
        this.registred = registred;
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    public List<TypeCost> getTypeCosts() {
        return typeCosts;
    }

    public void setTypeCosts(List<TypeCost> typeCosts) {
        this.typeCosts = typeCosts;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<CashAccountsAndCards> getCashAccountsAndCards() {
        return cashAccountsAndCards;
    }

    public void setCashAccountsAndCards(List<CashAccountsAndCards> cashAccountsAndCards) {
        this.cashAccountsAndCards = cashAccountsAndCards;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<TypeIncome> getTypeIncomes() {
        return typeIncomes;
    }

    public void setTypeIncomes(List<TypeIncome> typeIncomes) {
        this.typeIncomes = typeIncomes;
    }
}
