package locadora.util;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import locadora.model.BaseEntity;



@FacesConverter("util.SimpleEntityConverter")
public class SimpleEntityConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component, Object value) {

		if (value != null
				&& !"".equals(value)) {

			BaseEntity entity = (BaseEntity) value;

			// adiciona item como atributo do componente
			this.addAttribute(component, entity);

			Long codigo = entity.getCodigo();
			if (codigo != null) {
				return String.valueOf(codigo);
			}
		}

		return (String) value;
	}

	protected void addAttribute(UIComponent component, BaseEntity o) {
		String key = o.getCodigo().toString(); // codigo da entidade como chave neste caso
		this.getAttributesFrom(component).put(key, o);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
