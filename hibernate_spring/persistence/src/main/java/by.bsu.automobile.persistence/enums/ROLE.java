package by.bsu.automobile.persistence.enums;

import java.io.Serializable;

/**
 * Created by Sergey on 21.10.2016.
 */
public enum ROLE implements Serializable {
    ADMIN("Admin"), CLIENT("Client"), DEALER("Dealer");

    private String roleType;

    private ROLE(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }
}
