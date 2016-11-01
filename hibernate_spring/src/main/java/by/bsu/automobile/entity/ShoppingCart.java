package by.bsu.automobile.entity;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Sergey on 22.10.2016.
 */

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "date", nullable = false)
    private DateTime dateTime;

//    @OneToMany(mappedBy = "shoppingCart")
//    private Set<AutoDealer> automobiles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user != null) {
            this.user = user;
        }
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        if (dateTime != null) {
            this.dateTime = dateTime;
        }
    }

//    public Set<AutoDealer> getAutomobiles() {
//        return automobiles;
//    }
//
//    public void setAutomobiles(Set<AutoDealer> automobiles) {
//        if (automobiles != null) {
//            this.automobiles = automobiles;
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCart that = (ShoppingCart) o;

        if (id != that.id) return false;
        if (!user.equals(that.user)) return false;
        if (!dateTime.equals(that.dateTime)) return false;
//        return automobiles.equals(that.automobiles);
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + dateTime.hashCode();
//        result = 31 * result + automobiles.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", user=" + user +
                ", dateTime=" + dateTime +
//                ", automobiles=" + automobiles +
                '}';
    }
}
