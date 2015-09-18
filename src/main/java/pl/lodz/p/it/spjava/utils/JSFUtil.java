package pl.lodz.p.it.spjava.utils;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {

    public static void addMessage(String text, String details){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, text, details);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
