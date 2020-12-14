package web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

// Для того, чтобы в дальнейшим использовать класс User в Spring Security,
// он должен реализовывать интерфейс UserDetails.
// UserDetails можно представить, как адаптер между БД пользователей
// и тем что требуется Spring Security внутри SecurityContextHolder
@Entity
@Table(name = "t_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "login", length = 50)
    private String name; // уникальное значение

    @Column(name = "password", length = 50)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "t_users_t_role",
//            joinColumns = @JoinColumn(name = "user_id_user"),
//            inverseJoinColumns = @JoinColumn(name = "roles_id_role")
//    )
    private Set<Role> roles;

    @Transient
    private String admin;


    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public User(String name, String password, Set<Role> roles) {

        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
