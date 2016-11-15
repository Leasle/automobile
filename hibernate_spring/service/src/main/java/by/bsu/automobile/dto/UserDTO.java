package by.bsu.automobile.dto;

import by.bsu.automobile.persistence.enums.ROLE;

/**
 * Created by Sergey on 15.11.2016.
 */
public class UserDTO {
    private String login;
    private String password;
    private ROLE role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
