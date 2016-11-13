package by.bsu.automobile.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    @JoinColumn(name = "id_Auto")
    private Auto auto;

    @OneToOne
    @JoinColumn(name = "id_Dealer")
    private Dealer dealer;

    @Column(name = "cost", nullable = false)
    private double cost;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cart_auto_dealer", joinColumns = @JoinColumn(name = "id_Auto_Dealer"), inverseJoinColumns = @JoinColumn(name = "id_Shopping_Cart"))
    private Set<ShoppingCart> shoppingCartSet = new HashSet<ShoppingCart>();

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

    public Set<ShoppingCart> getShoppingCartSet() {
        return shoppingCartSet;
    }

    public void setShoppingCartSet(Set<ShoppingCart> shoppingCartSet) {
        this.shoppingCartSet = shoppingCartSet;
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
        return shoppingCartSet != null ? shoppingCartSet.equals(that.shoppingCartSet) : that.shoppingCartSet == null;

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
        result = 31 * result + (shoppingCartSet != null ? shoppingCartSet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AutoDealer{" +
                "id=" + id +
                ", auto=" + auto +
                ", dealer=" + dealer +
                ", cost=" + cost +
                ", shoppingCartSet=" + shoppingCartSet +
                '}';
    }
}
