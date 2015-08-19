package com.fing.proygrad.totalrecallbackoffice.views;


import com.vaadin.ui.Label;

public class ChannelsScreen extends BaseScreenLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3084673053358943678L;
	private static final String TITLE = "Canales de datos";
	
	
	public ChannelsScreen() {
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
		addComponent(new Label("channels..."));
	}

}
