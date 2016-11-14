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
@AssociationOverrides({
        @AssociationOverride(name = "autoDealerPK.auto",
                joinColumns = @JoinColumn(name = "id_Auto")),
        @AssociationOverride(name = "autoDealerPK.dealer",
                joinColumns = @JoinColumn(name = "id_Dealer")) })
public class AutoDealer implements Serializable {
    @EmbeddedId
    private AutoDealerPK autoDealerPK = new AutoDealerPK();

    @Column(name = "cost", nullable = false)
    private double cost;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "сart_auto_dealer", joinColumns = {@JoinColumn(name = "id_Auto_Auto_Dealer"),
            @JoinColumn(name = "id_Dealer_Auto_Dealer")}, inverseJoinColumns = @JoinColumn(name = "id_Shopping_Сart"))
    private Set<ShoppingCart> shoppingCartSet = new HashSet<ShoppingCart>();

    public AutoDealerPK getAutoDealerPK() {
        return autoDealerPK;
    }

    public void setAutoDealerPK(AutoDealerPK autoDealerPK) {
        this.autoDealerPK = autoDealerPK;
    }

    @Transient
    public Auto getAuto() {
        return getAutoDealerPK().getAuto();
    }

    public void setAuto(Auto auto) {
        getAutoDealerPK().setAuto(auto);
    }

    @Transient
    public Dealer getDealer() {
        return getAutoDealerPK().getDealer();
    }

    public void setDealer(Dealer dealer) {
        getAutoDealerPK().setDealer(dealer);
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

        if (Double.compare(that.cost, cost) != 0) return false;
        if (!autoDealerPK.equals(that.autoDealerPK)) return false;
//        return shoppingCartSet != null ? shoppingCartSet.equals(that.shoppingCartSet) : that.shoppingCartSet == null;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = autoDealerPK.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        result = 31 * result + (shoppingCartSet != null ? shoppingCartSet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AutoDealer{" +
                "autoDealerPK=" + autoDealerPK.toString() +
                ", cost=" + cost +
//                ", shoppingCartSet=" + shoppingCartSet +
                '}';
    }
}
