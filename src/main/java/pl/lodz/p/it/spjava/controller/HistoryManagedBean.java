package pl.lodz.p.it.spjava.controller;

import pl.lodz.p.it.spjava.model.Week;
import pl.lodz.p.it.spjava.service.MealService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

import static java.util.Collections.sort;

@ManagedBean(name = "historyMB")
@ViewScoped
public class HistoryManagedBean implements Serializable {

    @ManagedProperty(value = "#{MealService}")
    MealService mealService;

    private List<Week> weeks;

    @PostConstruct
    public void init() {
        weeks = mealService.getAllWeeks();
        sort(weeks);
    }

    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    public MealService getMealService() {
        return mealService;
    }

    public List<Week> getWeeks() {
        return weeks;
    }
}
