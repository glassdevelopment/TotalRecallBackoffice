package com.fing.proygrad.totalrecallbackoffice.views;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fing.proygrad.totalrecallbackoffice.storage.Channels;
import com.fing.proygrad.totalrecallbackoffice.util.Config;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

public class AddChannelWindow extends Window{

	private static final long serialVersionUID = 2755454347235944818L;
	private TextField channelName;
	private VerticalLayout lay, content;
	private Button save;
	private Window me = this;
	private List<String> types;
	private Config config;
	

	public AddChannelWindow(){
		try {
			config = new Config();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
	}
	
	private void init(){
		initComponents();
	}
	
	private void initComponents(){
		
		types = new Channels().getAvailableDataTypesForUser("123");
		
		content = new VerticalLayout();
		
		save = new Button(config.getMessage("channels.add.button.savebutton.caption"));
		save.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				try{
					
					Map<String, Object> data = new HashMap<String, Object>();
					//data.put("user_id", "123");
					data.put("type_name", channelName.getValue());
								
					HorizontalLayout comp;
					for(int i = 0; i < lay.getComponentCount(); i++){
						comp = (HorizontalLayout)lay.getComponent(i);
						((TextField)comp.getComponent(0)).validate();
						((ComboBox)comp.getComponent(1)).validate();
						data.put(((TextField)comp.getComponent(0)).getValue(), ((ComboBox)comp.getComponent(1)).getValue().toString());
						
					}
					
					Channels chan = new Channels();
					chan.createChannel("123", data);
					
					me.close();
					
				}catch(Exception e){
					e.printStackTrace();
					if(e instanceof InvalidValueException){
						Notification.show(e.getMessage());
					}
					
				}
				
				
			}
		});
		
		channelName = new TextField(config.getMessage("channels.add.textfield.channelname.caption"));
		channelName.setRequired(true);
		channelName.setRequiredError(config.getMessage("error.requiredfield", channelName.getCaption()));
		
		lay = new VerticalLayout();
		lay.addComponent(newAttribute());
		//attributes.setContent(lay);
		
		content.addComponent(channelName);
		content.addComponent(lay);
		lay.setSpacing(true);
		lay.setMargin(true);
		content.setMargin(true);
		content.setSpacing(true);
		this.setContent(content);
		this.center();
		this.setHeight("60%");
		this.setWidth("70%");
	}
	
	private HorizontalLayout newAttribute(){
		
		final HorizontalLayout hor = new HorizontalLayout();
		TextField attrName = new TextField(config.getMessage("channels.add.textfield.attrname.caption"));
		attrName.setRequired(true);
		attrName.setRequiredError(config.getMessage("error.requiredfield", attrName.getCaption()));
		ComboBox attrValue = new ComboBox(config.getMessage("channels.add.combobox.attrvalue.caption"));
		attrValue.setRequired(true);
		attrValue.setRequiredError(config.getMessage("error.requiredfield", attrValue.getCaption()));
	
		
		for(String type : types){
			attrValue.addItem(type);
		}
		
		Button addAttr = new Button("+");
		addAttr.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				lay.addComponent(newAttribute());
				hor.getComponent(2).setVisible(false);
				hor.getComponent(3).setVisible(false);
			}
		});
		
		
		
		
		hor.addComponent(attrName);
		hor.addComponent(attrValue);
		hor.addComponent(addAttr);
		hor.setComponentAlignment(addAttr, Alignment.BOTTOM_CENTER);
		hor.addComponent(save);
		hor.setComponentAlignment(save, Alignment.BOTTOM_CENTER);
		hor.setSpacing(true);
		
		return hor;
	}
	
}
