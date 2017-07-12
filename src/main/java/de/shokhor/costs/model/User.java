package de.shokhor.costs.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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
    private String password;

    @Column(name = "registred")
    private Date registred =new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "User_idUser"))
    @Column(name = "Role")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Cost> costs;

    @OneToMany(mappedBy = "user")
    private List<Group> groups;

    public User() {
    }

    public User(int id, String firstName, String sirname, String email, int age, String password, Date registred, Role role, List<Cost> costs, List<Group> groups) {
        super(id);
        this.firstName = firstName;
        this.sirname = sirname;
        this.email = email;
        this.age = age;
        this.password = password;
        this.registred = registred;
        this.role = role;
        this.costs = costs;
        this.groups=groups;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistred() {
        return registred;
    }

    public void setRegistred(Date registred) {
        this.registred = registred;
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }
}
