package com.fing.proygrad.totalrecallbackoffice.views;


import java.io.IOException;

import com.fing.proygrad.totalrecallbackoffice.util.Config;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class BaseScreenLayout extends VerticalLayout{


	private static final long serialVersionUID = 3498336544324058126L;
	private String title;
	private HorizontalLayout titleLayout;
	protected Config config;
	
//	public BaseScreenLayout() {
//	     initRoot();
//	     initComponents();
//	 }
	
	public BaseScreenLayout(String titleKey) {
		 try {
			this.config = new Config();
			this.title = config.getMessage(titleKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     initRoot();
	     initComponents();
	 }
	
	 private void initRoot(){
		 //setSizeFull();
	     setStyleName("base-screen");
	     addTitle();
	     initComponents();
	     resizeComponent();
	     
	 }
	 
	 private void addTitle(){
		 titleLayout = new HorizontalLayout();
		 titleLayout.addComponent(new Label(title));
		 addComponent(titleLayout);
	 }
	 
	 abstract protected void initComponents();
	 
	 protected void removeAllComponentsExceptTitle(){
		 
		 int compCount = getComponentCount();
		 
		 if(compCount > 1){
			 
			 for(int i = compCount - 1; i > 0; i--){
				 removeComponent(getComponent(i));
			 }
			 
		 }
		 
	 }
	 
	 private void resizeComponent(){
		 Component comp = (Component)this.getComponent(this.getComponentCount()-1);
	     setExpandRatio(comp, 1.0f);	     
	     setSpacing(true);
	 }

	 
}
