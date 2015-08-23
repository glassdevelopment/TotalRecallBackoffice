package com.fing.proygrad.totalrecallbackoffice.storage;



import org.mongodb.morphia.Morphia;

import com.fing.proygrad.totalrecallbackoffice.entities.User;
import com.fing.proygrad.totalrecallbackoffice.util.Config;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


public class MongoInit {



	private Config config;
	private MongoClient client;
	private MongoDatabase db;
	private Morphia morphia;
	
	public MongoInit(){
		
		client = new MongoClient((new MongoClientURI(config.getValue("mongodb.connectionstring"))));
		db = client.getDatabase(config.getValue("mongodb.dbname"));
		
		morphia = new Morphia();
		morphia.map(User.class);
	}
	
	
	
	public Morphia getMorphia() {
		return morphia;
	}



	public void setMorphia(Morphia morphia) {
		this.morphia = morphia;
	}



	public MongoClient getClient() {
		return client;
	}



	public void setClient(MongoClient client) {
		this.client = client;
	}



	public MongoDatabase getDb() {
		return db;
	}



	public void setDb(MongoDatabase db) {
		this.db = db;
	}



	
	
	
	
	
	
}
