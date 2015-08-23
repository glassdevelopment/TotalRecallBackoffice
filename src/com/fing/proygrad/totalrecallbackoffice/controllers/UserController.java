package com.fing.proygrad.totalrecallbackoffice.controllers;

import com.fing.proygrad.totalrecallbackoffice.dao.UserDAO;
import com.fing.proygrad.totalrecallbackoffice.entities.User;
import com.fing.proygrad.totalrecallbackoffice.interfaces.IController;
import com.fing.proygrad.totalrecallbackoffice.storage.MongoInit;

public class UserController implements IController{

	private UserDAO userDAO;
	
	public UserController(){
		MongoInit mongo = new MongoInit();
		userDAO = new UserDAO(mongo.getClient(), mongo.getMorphia(), mongo.getDb().getName());
	}
	
	@Override
	public void create(Object obj) throws Exception {		
		userDAO.create((User)obj);		
	}

	@Override
	public void delete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
