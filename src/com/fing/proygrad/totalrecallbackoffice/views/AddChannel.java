package com.fing.proygrad.totalrecallbackoffice.views;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

public class AddChannel extends Window{

	private static final long serialVersionUID = 2755454347235944818L;
	private TextField name;
	private Panel attributes;
	private VerticalLayout lay, content;
	private Button save;
	private Window me = this;
	

	public AddChannel(){
		init();
	}
	
	private void init(){
		initComponents();
	}
	
	private void initComponents(){
		
		content = new VerticalLayout();
		
		save = new Button("Guardar");
		save.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				me.close();
				
			}
		});
		
		name = new TextField("Nombre");
		attributes = new Panel("Atributos");
		lay = new VerticalLayout();
		lay.addComponent(newAttribute());
		//attributes.setContent(lay);
		
		content.addComponent(name);
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
		TextField attrName = new TextField("Nombre");
		ComboBox combo = new ComboBox("Tipo");
		combo.addItem("String");
		combo.addItem("Int");
		combo.addItem("Float");
		
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
		hor.addComponent(combo);
		hor.addComponent(addAttr);
		hor.setComponentAlignment(addAttr, Alignment.BOTTOM_CENTER);
		hor.addComponent(save);
		hor.setComponentAlignment(save, Alignment.BOTTOM_CENTER);
		hor.setSpacing(true);
		
		return hor;
	}
	
}
