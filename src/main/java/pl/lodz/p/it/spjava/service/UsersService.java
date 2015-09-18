package pl.lodz.p.it.spjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.it.spjava.dao.UsersRepository;
import pl.lodz.p.it.spjava.model.Users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service("UsersService")
@Transactional(readOnly = true)
public class UsersService {

    @Autowired
    UsersRepository usersDAO;

    public UsersRepository getUsersDAO() {
        return usersDAO;
    }

    public Users getUsersByUsername(String username) {
        if (username == null) {
            return null;
        }
        return getUsersDAO().getUsersByUsername(username);
    }

    public void addAdminUser(String username, String password) {
        if (isExisting(username)) {
            throw new RuntimeException("Uzytkownik " + username + " już istnieje!");
        }

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(encode(password));
        user.setAdmin(true);
        usersDAO.save(user);
    }

    private boolean isExisting(String username) {
        Users user = usersDAO.getUsersByUsername(username);
        return user != null;
    }

    public void addUser(String username, String password) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(encode(password));
        usersDAO.save(user);
    }

    public String encode(String password) {
        if (password == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encoded = md.digest(password.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte e : encoded) {
                builder.append(e);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("nie udało sie zakodować hasła.");
        }
    }
}
