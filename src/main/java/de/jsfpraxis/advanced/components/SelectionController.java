package de.jsfpraxis.advanced.components;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.inject.Named;

@Named
@RequestScoped
public class SelectionController {
	
	private String inputItem;
	private HtmlSelectOneMenu menu;
	
	public SelectionController() {

	}

	public void delete() {
		for (UIComponent component : menu.getChildren()) {
			UISelectItem selectItem = (UISelectItem) component;
			if (selectItem.getItemValue().equals(inputItem)) {
				menu.getChildren().remove(component);
			}
		}
	}
	
	
	public void add() {
		if (inputItem == null || inputItem.isBlank()) {
			return;
		}
		UISelectItem si = new UISelectItem();
		si.setItemLabel(inputItem);
		si.setItemValue(inputItem);
		menu.getChildren().add(si);
	}


	// Getter und Setter
	public String getInputItem() {
		return inputItem;
	}
	public void setInputItem(String inputItem) {
		this.inputItem = inputItem;
	}


	public HtmlSelectOneMenu getMenu() {
		return menu;
	}
	public void setMenu(HtmlSelectOneMenu menu) {
		this.menu = menu;
	}

}
