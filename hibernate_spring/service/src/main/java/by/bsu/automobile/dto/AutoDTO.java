package by.bsu.automobile.dto;

import java.sql.Date;

/**
 * Created by Sergey on 15.11.2016.
 */
public class AutoDTO {
    private String mark;
    private String model;
    private String specification;
    private Date year;

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
        this.year = year;
    }
}
