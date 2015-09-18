package pl.lodz.p.it.spjava.dao;

import com.google.common.base.Optional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.lodz.p.it.spjava.model.Meal;
import pl.lodz.p.it.spjava.model.MealType;
import pl.lodz.p.it.spjava.model.Week;

import java.util.List;

@Repository
public class MealRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addMeal(Meal meal) {
        getSessionFactory().getCurrentSession().save(meal);
    }

    public void saveWeek(Week week) {
        getSessionFactory().getCurrentSession().save(week);
    }

    public void deleteMeal(Meal meal) {
        getSessionFactory().getCurrentSession().delete(meal);
        getSessionFactory().getCurrentSession().flush();
    }

    public void updateMeal(Meal meal) {
        getSessionFactory().getCurrentSession().update(meal);
        getSessionFactory().getCurrentSession().flush();
    }

    public Meal getMealById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Meal  where id=?")
                .setParameter(0, id).list();
        return (Meal) list.get(0);
    }

    public List<Meal> getMeals() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Meal").list();
        return list;
    }

    public List<MealType> getMealsTypes() {
        List list = getSessionFactory().getCurrentSession().createQuery("from MealType").list();
        return list;
    }

    public List<Week> getAllWeeks() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Week").list();
        return list;
    }

    public Optional<Week> getWeekByNumber(int number) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Week where weekNumber=?")
                .setParameter(0, number).list();
        return list != null && !list.isEmpty() ? Optional.of((Week) list.get(0)) : Optional.<Week>absent();
    }

    public void deleteWeek(Week week) {
        getSessionFactory().getCurrentSession().delete(week);
        getSessionFactory().getCurrentSession().flush();
    }

}

