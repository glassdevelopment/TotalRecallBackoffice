package com.fing.proygrad.totalrecallbackoffice.views;


import com.fing.proygrad.totalrecallbackoffice.controllers.UserController;
import com.fing.proygrad.totalrecallbackoffice.entities.User;
import com.fing.proygrad.totalrecallbackoffice.exceptions.DuplicateEmailException;
import com.fing.proygrad.totalrecallbackoffice.util.StaticConfig;
import com.fing.proygrad.totalrecallbackoffice.util.security.PasswordEncryptionService;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class UserRegistrationScreen extends BaseScreenLayout{

	private static final long serialVersionUID = 5202627997548187346L;
	private VerticalLayout profile;
	
	private TextField firstName;		
	private TextField lastName;		
	private TextField email;		
	private PasswordField psw;		
	private PasswordField pswRepeat;
	private FormLayout form;

	public UserRegistrationScreen() {
		super("registration.screen.label.title");
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
		form = new FormLayout();
		
		//firstName = new TextField(config.getMessage("registration.screen.textfield.firstname.caption"));
		firstName = new TextField(StaticConfig.getMessage("registration.screen.textfield.firstname.caption"));
		firstName.setRequired(true);
		firstName.setRequiredError(config.getMessage("error.requiredfield", firstName.getCaption()));
		
		//lastName = new TextField(config.getMessage("registration.screen.textfield.lastname.caption"));
		lastName = new TextField(StaticConfig.getMessage("registration.screen.textfield.lastname.caption"));		
		
		email = new TextField(config.getMessage("registration.screen.textfield.email.caption"));		
		email.addValidator(new EmailValidator(config.getMessage("error.wrongemailformat")));
		email.setRequired(true);
		email.setRequiredError(config.getMessage("error.requiredfield", email.getCaption()));
		
		psw = new PasswordField(config.getMessage("registration.screen.textfield.psw.caption"));		
		psw.setRequired(true);
		psw.setRequiredError(config.getMessage("error.requiredfield", psw.getCaption()));
		
		pswRepeat = new PasswordField(config.getMessage("registration.screen.textfield.pswrepeat.caption"));
		pswRepeat.setRequired(true);
		pswRepeat.setRequiredError(config.getMessage("error.requiredfield", pswRepeat.getCaption()));

		Button createUser = new Button(config.getMessage("registration.screen.button.createuser.caption"));
		createUser.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {

				if(!psw.getValue().equals(pswRepeat.getValue())){
					Notification.show(config.getMessage("error.passwordmissmatch"));
				}else{
					
					try {
						
						firstName.validate();
						email.validate();
						psw.validate();
						pswRepeat.validate();
						
						User user = new User();
						user.setEmail(email.getValue());
						user.setFirstName(firstName.getValue());
						user.setLastName(lastName.getValue());
						
						PasswordEncryptionService pes = new PasswordEncryptionService();
					
						user.setPassword(pes.getEncryptedPassword(psw.getValue(), pes.generateSalt()));
						
						UserController contr = new UserController();
						contr.create(user);
						
					} catch (Exception e) {						
						e.printStackTrace();
						if(e instanceof DuplicateEmailException){
							Notification.show(config.getMessage("exception.duplicateemail"));
						}
						if(e instanceof InvalidValueException){
							Notification.show(e.getMessage());
						}
					} 
					
				}
				
			}
		});
		
		
		form.addComponent(firstName);
		form.addComponent(lastName);
		form.addComponent(email);
		form.addComponent(psw);
		form.addComponent(pswRepeat);
		
//		profile.addComponent(firstName);
//		profile.addComponent(lastName);
//		profile.addComponent(email);
//		profile.addComponent(psw);
//		profile.addComponent(pswRepeat);
		
		profile.addComponent(form);
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidth(pswRepeat.getWidth(), pswRepeat.getWidthUnits());
		buttonLayout.addComponent(createUser);
		buttonLayout.setComponentAlignment(createUser, Alignment.MIDDLE_RIGHT);		
		profile.addComponent(buttonLayout);
		//profile.addComponent(buttonLayout);
		buttonLayout.setMargin(true);
		
		addComponent(profile);
		profile.setMargin(true);
		
		Component comp = (Component)this.getComponent(this.getComponentCount()-1);
	    setExpandRatio(comp, 1.0f);
		
		
		
	}
	
	
	
	

}
