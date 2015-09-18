package pl.lodz.p.it.spjava.controller;

import org.joda.time.DateTime;
import pl.lodz.p.it.spjava.model.Week;
import pl.lodz.p.it.spjava.service.MealService;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@ManagedBean(name = "dishMB")
@ViewScoped
public class DishManagedBean implements Serializable{

    private static final int WEEKS_COUNT = 52;

    @ManagedProperty(value = "#{MealService}")
    MealService mealService;

    private Week week;

    private boolean meatOnFriday = false;

    private int weekNumber;

    private Date weekStartDate;

    private Date weekEndDate;

    private int currentWeekNumber;

    private List<Integer> weeksNumbers;

    @PostConstruct
    public void init() {
        initialize();
    }

    private void initialize() {
        currentWeekNumber = calculateCurrentWeekNumber();
        weekNumber = calculateDefaultWeekNumber(currentWeekNumber);
        weeksNumbers = generateWeekNumbersToEndOfCurrentYear();
        calculateWeekRangeDates();
    }

    private List<Integer> generateWeekNumbersToEndOfCurrentYear() {
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = weekNumber; i <= WEEKS_COUNT; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public void save() {
        week.setWeekNumber(weekNumber);
        week.setDateFrom(weekStartDate);
        week.setDateTo(weekEndDate);
        mealService.saveWeek(week);
        clear();
        initialize();
    }

    public void cancel() {
        clear();
        initialize();
    }

    private void clear() {
        week = null;
        meatOnFriday = false;
        weekNumber = 0;
        currentWeekNumber = 0;
        weeksNumbers = null;
    }

    public void calculateWeekRangeDates() {
        weekStartDate = new DateTime().withWeekOfWeekyear(weekNumber).toDate();
        weekEndDate = new DateTime().withWeekOfWeekyear(weekNumber + 1).toDate();
    }

    private int calculateDefaultWeekNumber(int currentWeekNumber) {
        return currentWeekNumber + 1;
    }

    private int calculateCurrentWeekNumber() {
        return new DateTime().getWeekOfWeekyear();
    }

    public void generate() {
        week = mealService.generateMenu(meatOnFriday);
    }

    public Week getWeek() {
        return week;
    }

    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    public boolean isMeatOnFriday() {
        return meatOnFriday;
    }

    public void setMeatOnFriday(boolean meatOnFriday) {
        this.meatOnFriday = meatOnFriday;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getCurrentWeekNumber() {
        return currentWeekNumber;
    }

    public void setCurrentWeekNumber(int currentWeekNumber) {
        this.currentWeekNumber = currentWeekNumber;
    }

    public List<Integer> getWeeksNumbers() {
        return weeksNumbers;
    }

    public void setWeeksNumbers(List<Integer> weeksNumbers) {
        this.weeksNumbers = weeksNumbers;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public Date getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

}
