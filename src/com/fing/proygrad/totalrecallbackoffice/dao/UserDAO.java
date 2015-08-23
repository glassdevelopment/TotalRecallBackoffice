package com.fing.proygrad.totalrecallbackoffice.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;




import com.fing.proygrad.totalrecallbackoffice.entities.User;
import com.fing.proygrad.totalrecallbackoffice.exceptions.DuplicateEmailException;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoClient;

public class UserDAO extends BasicDAO<User, ObjectId>{

	public UserDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

	public User create(User user) throws DuplicateEmailException{		
		try{
			getDatastore().ensureIndexes();
			getDatastore().save(user);		
			return user;
		}catch(DuplicateKeyException e){
			throw new DuplicateEmailException();
		}
		
		
	}
	
	public User get(String query, String value){
		
		
		
		List<User> users = getDatastore().find(User.class, query, value).asList();
		if(users != null && !users.isEmpty()){
			return users.get(0);
		}
		
		return null;
	}
	
	
}
