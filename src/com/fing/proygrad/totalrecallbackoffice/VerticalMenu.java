package com.fing.proygrad.totalrecallbackoffice;

import java.util.ArrayList;

import com.fing.proygrad.totalrecallbackoffice.views.ChannelsScreen;
import com.fing.proygrad.totalrecallbackoffice.views.DevicesScreen;
import com.fing.proygrad.totalrecallbackoffice.views.ProfileScreen;
import com.fing.proygrad.totalrecallbackoffice.views.UserRegistrationScreen;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class VerticalMenu extends VerticalLayout{

    /**
     *
     */
    private static final long serialVersionUID;
    private static final String PROFILE_BUTTON;
    private static final String DATA_BUTTON;
    private static final String DEVICES_BUTTON;
    private static final String REGISTRATION_BUTTON;
    private VerticalLayout lay;
    
    static{
        serialVersionUID = -1508291768897794634L;
        PROFILE_BUTTON = "Perfil";
        DATA_BUTTON = "Datos";
        DEVICES_BUTTON = "Dispositivos";
        REGISTRATION_BUTTON = "Registrarse";
        
    }
    //initialize root & other components
    public VerticalMenu(){
        initRoot();
        initComponents();
    }
    private void initRoot(){
        setStyleName("vertical-menu");
        lay = this;
    }
    private void initComponents(){
        initButtons();
    }
    
    private void initButtons(){
    	
    	Button profile = new Button(PROFILE_BUTTON);
    	Button data = new Button(DATA_BUTTON);
    	Button devices = new Button(DEVICES_BUTTON);
    	Button registration = new Button(REGISTRATION_BUTTON);
    	
    	profile.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				((HorizontalSplitPanel)lay.getParent()).setSecondComponent(new ProfileScreen());
				
			}
		});
    	
    	data.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				((HorizontalSplitPanel)lay.getParent()).setSecondComponent(new ChannelsScreen());
				
			}
		});
    	
    	devices.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				//((HorizontalSplitPanel)lay.getParent()).removeComponent(((HorizontalSplitPanel)lay.getParent()).getSecondComponent());
				((HorizontalSplitPanel)lay.getParent()).setSecondComponent(new DevicesScreen());
				
			}
		});
    	
    	registration.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				//((HorizontalSplitPanel)lay.getParent()).removeComponent(((HorizontalSplitPanel)lay.getParent()).getSecondComponent());
				((HorizontalSplitPanel)lay.getParent()).setSecondComponent(new UserRegistrationScreen());
				
			}
		});
    	
    	
    	
    	addComponent(profile);
    	addComponent(data);
    	addComponent(devices);
    	addComponent(registration);
    	
    	Button but = (Button)getComponent(getComponentCount()-1);
        setExpandRatio(but, 1.0f);
        
        
        
        setSizeFull();
    }
    


}
