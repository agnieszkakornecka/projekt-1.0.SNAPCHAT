package pl.lodz.p.it.spjava.controller;

import pl.lodz.p.it.spjava.service.UsersService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

import static pl.lodz.p.it.spjava.utils.JSFUtil.addMessage;

@ManagedBean(name = "addUserMB")
@ViewScoped
public class AddUserManagedBean implements Serializable {


    @ManagedProperty(value = "#{UsersService}")
    UsersService usersService;

    private String username;
    private String password;
    private String confirmation;
    private boolean admin = false;

    private static final String EMPTY = "";

    public void addUser() {
        try {
            if (username == null || EMPTY.equals(username)) {
                addMessage("Blad danych. Wprowadz nazwe uzytkownika.", " ");
                return;
            }
            if (password == null || EMPTY.equals(password)) {
                addMessage("Blad danych. Wprowadz haslo.", " ");
                return;
            }
            if (password.length() < 8) {
                addMessage("Blad danych. Haslo musi miec min. 8 znakow.", " ");
                return;
            }
            if (!password.equals(confirmation)) {
                addMessage("Blad danych. Niepoprawne haslo.", " ");
                return;
            }
            if (admin) {
                usersService.addAdminUser(username, password);
            } else {
                usersService.addUser(username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addMessage("Blad danych.", e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public UsersService getUsersService() {
        return usersService;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
