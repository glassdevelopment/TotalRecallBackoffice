package com.fing.proygrad.totalrecallbackoffice;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.Reindeer;

public class HorizontalMenu extends HorizontalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4114201431325617854L;
	
	
	{
        initRoot();
        initComponents();
    }
    private void initRoot(){
        setStyleName("horizontal-menu");
    }
    private void initComponents(){
       initButtons();
    }
    
    private void initButtons(){
    	setWidth("100%");
    	logoutButton = new Button("Salir");
    	//logoutButton.setStyleName(Reindeer.BUTTON_LINK);
    	//logoutButton.addStyleName("logoutlink");
    	addComponent(logoutButton);
    	setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);
    	
    }
    
    private Button logoutButton;
	

}
