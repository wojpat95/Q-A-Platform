package QAPlatform.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Klasa będąca modelem uzytkownika aplikacji.
 * @author Amadeusz Mileszko
 */

@Entity
@Table(name = "user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private Set<Role> roles;

    /**
    * @return identyfikator użytkownika
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * @param id 
     *      identyfikator użytkownika
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    * @return nazwa użytkownika
    */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *      nazwa użytkownika
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
    * @return hasło użytkownika
    */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *      hasło użytkownika
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
    * @return potwierdzenie hasła użytkownika
    */
    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * @param passwordConfirm
     *      potwierdzenie hasła użytkownika
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    /**
    * @return role użytkownika
    */
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles
     *      role użytkownika
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}