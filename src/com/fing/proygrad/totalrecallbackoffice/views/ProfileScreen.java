package com.fing.proygrad.totalrecallbackoffice.views;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ProfileScreen extends BaseScreenLayout{

	private static final long serialVersionUID = 5202627997548187346L;
	private static final String TITLE = "Perfil de usuario";
	private VerticalLayout profile;
	
	public ProfileScreen() {
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
		
		profile = new VerticalLayout();
		Label firstNameLbl, lastName, passwordLbl, emailLbl, profilePictureLbl;
		HorizontalLayout nameLay, lastNameLay, passLay, emailLay, profilePictureLay;
		
		firstNameLbl = new Label("Nombre");
		passwordLbl = new Label("Password");
		emailLbl = new Label("Email");
		profilePictureLbl = new Label("Foto");
		
		TextField name = new TextField("Nombre");
		name.setValue("Gonzalo Lopez");
		
		PasswordField psw = new PasswordField("Password");
		psw.setValue("1234");
		psw.setEnabled(false);
		
		TextField email = new TextField("Email");
		email.setValue("lopez.m.gonzalo@gmail.com");
		email.addValidator(new EmailValidator("Formato de email incorrecto"));
		
		profile.addComponent(name);
		profile.addComponent(psw);
		profile.addComponent(email);
		
		
		addComponent(profile);
		
		Component comp = (Component)this.getComponent(this.getComponentCount()-1);
	    setExpandRatio(comp, 1.0f);
		
//		nameLay = new HorizontalLayout();
//		nameLay.addComponent(firstNameLbl);
		
		
	}
	
	
	
	

}
