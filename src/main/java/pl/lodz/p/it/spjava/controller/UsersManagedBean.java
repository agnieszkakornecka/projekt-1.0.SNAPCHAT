package pl.lodz.p.it.spjava.controller;


import pl.lodz.p.it.spjava.model.Users;
import pl.lodz.p.it.spjava.service.UsersService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

import static pl.lodz.p.it.spjava.utils.JSFUtil.addMessage;

@ManagedBean(name = "usersMB")
@SessionScoped
public class UsersManagedBean implements Serializable {

    @ManagedProperty(value = "#{UsersService}")
    UsersService usersService;

    private String username;
    private String password;

    private Users user;

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

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    public String login() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (!tryLog()) {
            addMessage("Blad logowania. Niepoprawny login lub haslo.", " ");
            return null;
        } else {
            addMessage("Witamy!", username);
            if (session != null) {
                session.setAttribute("loggedIn", true);
            }
            return "/index.xhtml";
        }

    }

    private boolean tryLog() {
        if (username != null && !"".equals(username)) {
            Users user = usersService.getUsersByUsername(username);
            if (user != null) {
                String endoded = usersService.encode(password);
                if (endoded != null && endoded.equals(user.getPassword())) {
                    this.user = user;
                    return true;
                }
            }
        }
        return false;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        user = null;
        return "/login.xhtml";
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
