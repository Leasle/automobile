package by.bsu.automobile.enums;

import java.io.Serializable;

/**
 * Created by Sergey on 21.10.2016.
 */
public enum ROLE implements Serializable {
    ADMIN("admin"), USER("user"), DEALER("dealer");

    private String roleType;

    private ROLE(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }
}
