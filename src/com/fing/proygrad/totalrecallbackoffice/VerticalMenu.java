package com.fing.proygrad.totalrecallbackoffice;

import java.util.ArrayList;

import com.fing.proygrad.totalrecallbackoffice.views.ChannelsScreen;
import com.fing.proygrad.totalrecallbackoffice.views.DevicesScreen;
import com.fing.proygrad.totalrecallbackoffice.views.ProfileScreen;
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
    private HorizontalSplitPanel parent;
    private VerticalLayout lay;
    
    static{
        serialVersionUID = -1508291768897794634L;
        PROFILE_BUTTON = "Perfil";
        DATA_BUTTON = "Datos";
        DEVICES_BUTTON = "Dispositivos";
    }
    //initialize root & other components
    public VerticalMenu(){
        initRoot();
        initComponents();
    }
    private void initRoot(){
        setStyleName("vertical-menu");
        lay = this;
        parent = (HorizontalSplitPanel)this.getParent();
    }
    private void initComponents(){
        initButtons();
    }
    
    private void initButtons(){
    	
    	Button profile = new Button(PROFILE_BUTTON);
    	Button data = new Button(DATA_BUTTON);
    	Button devices = new Button(DEVICES_BUTTON);
    	
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
    	
    	addComponent(profile);
    	addComponent(data);
    	addComponent(devices);
    	
    	Button but = (Button)getComponent(getComponentCount()-1);
        setExpandRatio(but, 1.0f);
        
        
        
        setSizeFull();
    }
    
    private void initButtons2(){
    	
    	
        buttons    = new ArrayList<>();
        
        Button profile = new Button("Perfil");
        Button data = new Button("Datos");
        Button devices = new Button("Dispositivos");
        
        buttons.add(profile);
        //setExpandRatio(profile, 1.0f);
        buttons.add(data);
        //setExpandRatio(data, 1.0f);
        buttons.add(devices);
        //setExpandRatio(devices, 1.0f);
        
        
        for(Button button:buttons){
        	
        	button.addClickListener(new Button.ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					Notification.show("i'm "+event.getButton().getCaption()+" Button");
					
				}
			});
        	
//            button.addClickListener((clickEvent)->{
//                Notification.show("i'm "+button.getCaption()+" Button");
//            });
//            switch(button.getCaption()){
//            case "Perfil":{button.setIcon(FontAwesome.USER);}break;
//            case "Datos":{button.setIcon(FontAwesome.DATABASE);}break;
//            case "Dispositivos":{button.setIcon(FontAwesome.ANDROID);}break;
//            
//            }
            addComponent(button);
            
        }
        
        Button but = (Button)getComponent(getComponentCount()-1);
        setExpandRatio(but, 1.0f);
        
        
        
        setSizeFull();
        
    }
    private ArrayList<Button> buttons;

}
