package pl.lodz.p.it.spjava.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@ManagedBean(name = "positionMB")
@ViewScoped
public class MealPositionManagedBean implements Serializable {

    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();


    @PostConstruct
    public void init() {

    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }


//    public void displayPosition() {
//        FacesMessage msg;
//        if(mealPosition != null){
//            msg = new FacesMessage("Selected", mealPosition);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//    } else {
//            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Meal position is not selected.");
//        }
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
}
