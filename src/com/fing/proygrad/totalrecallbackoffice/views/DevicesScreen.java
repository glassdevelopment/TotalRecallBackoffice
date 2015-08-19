package com.fing.proygrad.totalrecallbackoffice.views;


import com.vaadin.ui.Label;

public class DevicesScreen extends BaseScreenLayout{


	private static final long serialVersionUID = 7403747238298538730L;
	private static final String TITLE = "Dispositivos Registrados";

	public DevicesScreen() {
		super(TITLE);
		init();
	}
	
	private void init(){
		initComponents();
	}

	@Override
	protected void initComponents(){
		//removeAllComponents();
		removeAllComponentsExceptTitle();
		addComponent(new Label("a"));
	}

}
