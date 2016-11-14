package by.bsu.automobile.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sergey on 13.11.2016.
 */

@Embeddable
public class AutoDealerPK implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Auto")
    private Auto auto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Dealer")
    private Dealer dealer;

    public AutoDealerPK() {}

    public AutoDealerPK(Auto auto, Dealer dealer) {
        this.auto = auto;
        this.dealer = dealer;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoDealerPK that = (AutoDealerPK) o;

        if (!auto.equals(that.auto)) return false;
        return dealer.equals(that.dealer);

    }

    @Override
    public int hashCode() {
        int result = auto.hashCode();
        result = 31 * result + dealer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AutoDealerPK{" +
                "auto=" + auto +
                ", dealer=" + dealer +
                '}';
    }
}
