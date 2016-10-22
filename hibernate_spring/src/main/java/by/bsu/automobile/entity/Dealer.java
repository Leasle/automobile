package by.bsu.automobile.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sergey on 22.10.2016.
 */

@Entity
@Table(name = "dealer")
public class Dealer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "address", length = 45)
    private String address;

    @OneToOne(mappedBy = "dealer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AutoDealer autoDealer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 0) {
            this.name = name;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() > 0) {
            this.address = address;
        }
    }

    public AutoDealer getAutoDealer() {
        return autoDealer;
    }

    public void setAutoDealer(AutoDealer autoDealer) {
        if (autoDealer != null) {
            this.autoDealer = autoDealer;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dealer dealer = (Dealer) o;

        if (id != dealer.id) return false;
        if (!name.equals(dealer.name)) return false;
        if (address != null ? !address.equals(dealer.address) : dealer.address != null) return false;
        return autoDealer != null ? autoDealer.equals(dealer.autoDealer) : dealer.autoDealer == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (autoDealer != null ? autoDealer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", autoDealer=" + autoDealer +
                '}';
    }
}
