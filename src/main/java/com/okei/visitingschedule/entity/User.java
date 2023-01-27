package com.okei.visitingschedule.entity;

import com.okei.visitingschedule.entity.schedule.Position;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {

    private static final long serialVersionUID = 5038821598380436607L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String password;
    private boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    private String lastname;
    private String firstname;
    private String middlename;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    public User(String username, String password, boolean active,Position position, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.position = position;
        this.roles = roles;
    }

    public User(String username, String password, boolean active,Position position, Set<Role> roles, String lastname, String firstname, String middlename) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.position = position;
        this.roles = roles;
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
    }

    public User(String username, String password, boolean active,Position position, Set<Role> roles, String lastname, String firstname) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.position = position;
        this.roles = roles;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public User() {
    }

    public boolean isAdmin(){
        return roles.contains(Role.ADMIN);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return active == user.active && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(lastname, user.lastname) && Objects.equals(firstname, user.firstname) && Objects.equals(middlename, user.middlename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, lastname, firstname, middlename, active);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", midlename='" + middlename + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
