package by.bsu.automobile.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "autoDealerPK.dealer", cascade = CascadeType.ALL)
    private Set<AutoDealer> autoDealers = new HashSet<AutoDealer>();

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

    public Set<AutoDealer> getAutoDealers() {
        return autoDealers;
    }

    public void setAutoDealers(Set<AutoDealer> autoDealers) {
        this.autoDealers = autoDealers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dealer dealer = (Dealer) o;

        if (id != dealer.id) return false;
        if (!name.equals(dealer.name)) return false;
        return address != null ? address.equals(dealer.address) : dealer.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
