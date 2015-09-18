package pl.lodz.p.it.spjava.controller;

import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;
import pl.lodz.p.it.spjava.service.MealService;
import pl.lodz.p.it.spjava.model.Meal;
import pl.lodz.p.it.spjava.model.MealType;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "mealMB")
@ViewScoped
public class MealManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    @ManagedProperty(value = "#{MealService}")
    MealService mealService;

    long mealTypeId;
    private String mealPosition;
    private String mealName;

    long mealToEditTypeId;

    private boolean ifReloadList = false;

    private Map<String, String> positions;

    List<MealType> mealTypes = new ArrayList<MealType>();

    List<Meal> meals = new ArrayList<Meal>();

    private Meal mealToEdit = new Meal();

    public String getMealPosition() {
        return mealPosition;
    }

    public void setMealPosition(String mealPosition) {
        this.mealPosition = mealPosition;
    }

    public Map<String, String> getPositions() {
        return positions;
    }

    @PostConstruct
    public void init() {
        mealTypes = mealService.getMealTypes();
        meals = mealService.getMeals();
        initPositions();
    }

    private void initPositions() {
        positions = new HashMap<String, String>();
        positions.put("First meal", "First meal");
        positions.put("Second meal", "Second meal");
        positions.put("Dessert", "Dessert");
    }

    public String addMeal() {
        try {
            Meal meal = new Meal();
            meal.setMealType(getMealType());
            meal.setName(getMealName());
            meal.setMealPosition(getMealPosition());
            getMealService().addMeal(meal);
            ifReloadList = true;
            return null;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return ERROR;
    }

    public void edit(Long id) {
        mealToEdit = findMealToEdit(id);
        if (mealToEdit == null) {
            return;
        }
        mealToEditTypeId = mealToEdit.getMealType().getId();
        RequestContext.getCurrentInstance().execute("PF('editDialog').show()");
    }

    private Meal findMealToEdit(Long id) {
        for (Meal meal : meals) {
            if (id != null && id.equals(meal.getId())) {
                return meal;
            }
        }
        return null;
    }

    public void delete(Long id) {
        Meal meal = findMealToDelete(id);
        if (meal == null) {
            return;
        }
        getMealService().deleteMeal(meal);
        ifReloadList = true;
    }

    private Meal findMealToDelete(Long id) {
        for (Meal meal : meals) {
            if (id != null && id.equals(meal.getId())) {
                return meal;
            }
        }
        return null;
    }

    public void updateMeal() {
        try {
            mealToEdit.setMealType(getMealToEditType());
            mealService.updateMeal(mealToEdit);
            RequestContext.getCurrentInstance().execute("PF('editDialog').hide()");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public List<MealType> getMealTypes() {
        return mealTypes;
    }

    public void setMealTypes(final List<MealType> mealTypes) {
        this.mealTypes = mealTypes;
    }

    public MealService getMealService() {
        return mealService;
    }

    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    public long getMealTypeId() {
        return mealTypeId;
    }

    public void setMealTypeId(final long mealTypeId) {
        this.mealTypeId = mealTypeId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(final String mealName) {
        this.mealName = mealName;
    }

    public MealType getMealType() {
        for (MealType type : mealTypes) {
            if (type.getId() == mealTypeId) {
                return type;
            }
        }
        throw new RuntimeException("Chosen type of meal have to exist in dictionary.");
    }

    public MealType getMealToEditType() {
        for (MealType type : mealTypes) {
            if (type.getId() == mealToEditTypeId) {
                return type;
            }
        }
        throw new RuntimeException("Chosen type of meal have to exist in dictionary.");
    }

    public List<Meal> getMeals() {
        if (ifReloadList) {
            meals = mealService.getMeals();
            ifReloadList = false;
        }
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Meal getMealToEdit() {
        return mealToEdit;
    }

    public long getMealToEditTypeId() {
        return mealToEditTypeId;
    }

    public void setMealToEditTypeId(long mealToEditTypeId) {
        this.mealToEditTypeId = mealToEditTypeId;
    }

    public void setMealToEdit(Meal mealToEdit) {
        this.mealToEdit = mealToEdit;
    }

}
