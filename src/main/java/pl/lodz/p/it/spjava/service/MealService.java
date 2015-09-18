package pl.lodz.p.it.spjava.service;

import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.it.spjava.dao.MealRepository;
import pl.lodz.p.it.spjava.model.Day;
import pl.lodz.p.it.spjava.model.Meal;
import pl.lodz.p.it.spjava.model.MealType;
import pl.lodz.p.it.spjava.model.Week;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("MealService")
@Transactional
public class MealService {

    private static final String EMPTY = "";
    @Autowired
    MealRepository mealDAO;

    public MealRepository getMealDAO() {
        return mealDAO;
    }

    public void setMealDAO(MealRepository mealDAO) {
        this.mealDAO = mealDAO;
    }

    public List<Meal> getMeals() {
        return getMealDAO().getMeals();
    }

    @Transactional(readOnly = false)
    public void addMeal(Meal meal) {
        if (meal == null) {
            return;
        }
        getMealDAO().addMeal(meal);
    }

    @Transactional(readOnly = false)
    public void deleteMeal(Meal meal) {
        if (meal == null) {
            return;
        }
        getMealDAO().deleteMeal(meal);
    }

    @Transactional(readOnly = false)
    public void updateMeal(Meal meal) {
        if (meal == null) {
            return;
        }
        getMealDAO().updateMeal(meal);
    }

    public List<MealType> getMealTypes() {
        return mealDAO.getMealsTypes();
    }

    public Week generateMenu(boolean isMeat) {
        //pobieranie wszystkich
        List<Meal> meals = getMeals();

        //filtrowanie po pozycjach
        List<Meal> firstMeals = getMealsByPosition(meals, "First meal");
        List<Meal> secondMeals = getMealsByPosition(meals, "Second meal");
        List<Meal> dessertMeals = getMealsByPosition(meals, "Dessert");

        //generowanie tygodnia , 5 dni
        Week week = new Week();

        for (int i = 0; i < 5; i++) {
            Day day = new Day();
            if (i == 4) {
                day.setFirst(getUniqueFirstMeal(firstMeals, week, isMeat));
                day.setSecond(getUniqueSecondMeal(secondMeals, week, isMeat));
                day.setDessert(getUniqueDessert(dessertMeals, week, isMeat));
            } else {
                day.setFirst(getUniqueFirstMeal(firstMeals, week, true));
                day.setSecond(getUniqueSecondMeal(secondMeals, week, true));
                day.setDessert(getUniqueDessert(dessertMeals, week, true));
            }
            week.addDay(day);
        }
        return week;
    }

    private String getUniqueDessert(final List<Meal> dessertMeals, final Week week, boolean isMeat) {
        Meal meal = null;
        boolean isExisting = true;
        while (isExisting) {
            meal = dessertMeals.get(randomFromZeroTo(dessertMeals.size()));
            if (meal.getMealType().getType().equals("meat") && !isMeat) {
                continue;
            }
            isExisting = isExistingDessertMeal(meal, week);
        }
        return meal != null ? meal.getName() : EMPTY;
    }

    private String getUniqueSecondMeal(final List<Meal> secondMeals, final Week week, boolean isMeat) {
        Meal meal = null;
        boolean isExisting = true;
        while (isExisting) {
            meal = secondMeals.get(randomFromZeroTo(secondMeals.size()));
            if (meal.getMealType().getType().equals("meat") && !isMeat) {
                continue;
            }
            isExisting = isExistingSecondMeal(meal, week);
        }
        return meal != null ? meal.getName() : EMPTY;
    }

    private String getUniqueFirstMeal(final List<Meal> firstMeals, final Week week, boolean isMeat) {
        Meal meal = null;
        boolean isExisting = true;
        while (isExisting) {
            meal = firstMeals.get(randomFromZeroTo(firstMeals.size()));
            if (meal.getMealType().getType().equals("meat") && !isMeat) {
                continue;
            }
            isExisting = isExistingFirstMeal(meal, week);
        }
        return meal != null ? meal.getName() : EMPTY;
    }

    private boolean isExistingFirstMeal(Meal meal, Week week) {
        for (Day day : week.getDays()) {
            if (day.getFirst().equals(meal.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistingSecondMeal(Meal meal, Week week) {
        for (Day day : week.getDays()) {
            if (day.getSecond().equals(meal.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistingDessertMeal(Meal meal, Week week) {
        for (Day day : week.getDays()) {
            if (day.getDessert().equals(meal.getName())) {
                return true;
            }
        }
        return false;
    }

    private int randomFromZeroTo(int to) {
        Random random = new Random();
        return random.nextInt(to);
    }

    private List<Meal> getMealsByPosition(final List<Meal> meals, String position) {
        List<Meal> list = new ArrayList<Meal>();
        for (Meal meal : meals) {
            if (position.equals(meal.getMealPosition())) {
                list.add(meal);
            }
        }
        return list;
    }

    public List<Week> getAllWeeks() {
        return mealDAO.getAllWeeks();
    }

    public void saveWeek(Week week) {
        Optional<Week> existingWeek = mealDAO.getWeekByNumber(week.getWeekNumber());
        if (existingWeek.isPresent()) {
            mealDAO.deleteWeek(existingWeek.get());
        }
        getMealDAO().saveWeek(week);
    }
}

