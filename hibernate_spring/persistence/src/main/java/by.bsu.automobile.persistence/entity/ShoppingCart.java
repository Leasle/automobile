package by.bsu.automobile.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey on 22.10.2016.
 */

@Entity
@Table(name = "shopping_сart")
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_User")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date dateTime;

    @ManyToMany(mappedBy = "shoppingCartSet", fetch = FetchType.EAGER)
    private Set<AutoDealer> autoDealers = new HashSet<AutoDealer>();

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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        if (dateTime != null) {
            this.dateTime = dateTime;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCart that = (ShoppingCart) o;

        if (id != that.id) return false;
        if (!user.equals(that.user)) return false;
        return dateTime.equals(that.dateTime);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + dateTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", user=" + user +
                ", dateTime=" + dateTime +
                ", autoDealers=" + autoDealers +
                '}';
    }
}
