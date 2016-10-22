package by.bsu.automobile.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sergey on 22.10.2016.
 */

@Entity
@Table(name = "auto_dealer")
public class AutoDealer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_auto")
    private Auto auto;

    @OneToOne
    @JoinColumn(name = "id_dealer")
    private Dealer dealer;

    @Column(name = "cost", nullable = false)
    private double cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "cart_auto_dealer", joinColumns = @JoinColumn(name = "id_auto_dealer"), inverseJoinColumns = @JoinColumn(name = "id_shopping_cart"))
    private ShoppingCart shoppingCart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        if (auto != null) {
            this.auto = auto;
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        if (dealer != null) {
            this.dealer = dealer;
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost >= 0) {
            this.cost = cost;
        }
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCart != null) {
            this.shoppingCart = shoppingCart;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoDealer that = (AutoDealer) o;

        if (id != that.id) return false;
        if (Double.compare(that.cost, cost) != 0) return false;
        if (!auto.equals(that.auto)) return false;
        if (!dealer.equals(that.dealer)) return false;
        return shoppingCart != null ? shoppingCart.equals(that.shoppingCart) : that.shoppingCart == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + auto.hashCode();
        result = 31 * result + dealer.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (shoppingCart != null ? shoppingCart.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AutoDealer{" +
                "id=" + id +
                ", auto=" + auto +
                ", dealer=" + dealer +
                ", cost=" + cost +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}
