package converters;

import entities.Categorieintervention;
import controllers.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import sessions.CategorieinterventionFacadeLocal;

@FacesConverter(value = "categorieinterventionConverter")
public class CategorieinterventionConverter implements Converter {

    @Inject
    private CategorieinterventionFacadeLocal ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Categorieintervention) {
            Categorieintervention o = (Categorieintervention) object;
            return getStringKey(o.getIdcategorieintervention());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of groupe {1}; expected groupe: {2}", new Object[]{object, object.getClass().getName(), Categorieintervention.class.getName()});
            return null;
        }
    }

}
