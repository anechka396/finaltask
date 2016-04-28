package by.epam.likeit.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private String name;
    private Role role;
    private String email;

    public User() {
        String empty = "";
        this.login = empty;
        this.password = empty;
        this.name = empty;
        this.email = empty;
    }

    public User(String login, String password, String name, Role role, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
