package pl.lodz.p.it.spjava.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.lodz.p.it.spjava.model.Users;

import java.util.List;

@Repository
public class UsersRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Users getUsersByUsername(String username) {
        List<Users> list = getSessionFactory().getCurrentSession()
                .createQuery("from Users  where username=?")
                .setParameter(0, username).list();
        return list.isEmpty() ? null : list.get(0);
    }

    public void save(Users user) {
        getSessionFactory().getCurrentSession().save(user);
        getSessionFactory().getCurrentSession().flush();
    }
}
