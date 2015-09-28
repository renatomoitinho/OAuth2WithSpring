package br.inf.audasi.domain.entity;

import br.inf.audasi.domain.entity.auth.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Table(name = "users")
public @Entity class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1, initialValue = 1)
    private @Id Long id;

    @Column(name = "LOGIN", length = 50, nullable = false, unique = true)
    private String login;

    @Column(name = "NAME", length = 120, nullable = false, unique = true)
    private String name;

    @Column(name = "EMAIL", length = 120, nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(User user) {
        super();
        this.id = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
