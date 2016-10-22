package by.bsu.automobile.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Sergey on 22.10.2016.
 */

@Entity
@Table(name = "auto")
public class Auto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mark", nullable = false, length = 45)
    private String mark;

    @Column(name = "model", nullable = false, length = 45)
    private String model;

    @Column(name = "spec", length = 45)
    private String specification;

    @Column(name = "year", nullable = false)
    private Date year;

    @OneToOne(mappedBy = "auto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AutoDealer autoDealer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        if (year != null) {
            this.year = year;
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

        Auto auto = (Auto) o;

        if (id != auto.id) return false;
        if (!mark.equals(auto.mark)) return false;
        if (!model.equals(auto.model)) return false;
        if (!specification.equals(auto.specification)) return false;
        if (!year.equals(auto.year)) return false;
        return autoDealer.equals(auto.autoDealer);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mark.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + specification.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + autoDealer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", specification='" + specification + '\'' +
                ", year=" + year +
                ", autoDealer=" + autoDealer +
                '}';
    }
}
