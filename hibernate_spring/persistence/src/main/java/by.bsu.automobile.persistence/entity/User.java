package by.bsu.automobile.persistence.entity;

import by.bsu.automobile.persistence.enums.ROLE;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergey on 21.10.2016.
 */

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "user_gen_seq", sequenceName = "user_seq")
    private int id;

    @Column(unique = true, nullable = false, length = 45, name = "login")
    private String login;

    @Column(nullable = false, length = 45, name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private ROLE role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private UserData userData;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<ShoppingCart> shoppingCarts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login.length() > 3) {
            this.login = login;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() > 7) {
            this.password = password;
        }
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        if (userData != null) {
            this.userData = userData;
        }
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCart(List<ShoppingCart> shoppingCarts) {
        if (shoppingCarts != null) {
            this.shoppingCarts = shoppingCarts;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (role != user.role) return false;
        if (!userData.equals(user.userData)) return false;
        return shoppingCarts != null ? shoppingCarts.equals(user.shoppingCarts) : user.shoppingCarts == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + userData.hashCode();
        result = 31 * result + (shoppingCarts != null ? shoppingCarts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", userData=" + userData +
                ", shoppingCarts=" + shoppingCarts +
                '}';
    }
}
