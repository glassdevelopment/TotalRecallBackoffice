package com.fing.proygrad.totalrecallbackoffice.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.fing.proygrad.totalrecallbackoffice.util.Config;
import com.fing.proygrad.totalrecallbackoffice.util.Consts;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

public class Channels {

	private Config config;
	private static final String COLLECTION_NAME = "channels";
	
	private MongoClient client;
	private MongoDatabase db;
	
	public Channels(){
		try {
			config = new Config();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client = new MongoClient((new MongoClientURI(config.getValue("mongodb.connectionstring"))));
		db = client.getDatabase(config.getValue("mongodb.dbname"));
	}
	
	
	public List<String> getUserChannels(String userId){
		
		final List<String> userChannels = new ArrayList<String>();		
		
		FindIterable<Document> iterable = db.getCollection(COLLECTION_NAME).find(new Document("user_id", userId)).projection(Projections.exclude("_id" , "user_id"));				
		
		iterable.forEach(new Block<Document>(){

			@Override
			public void apply(Document t) {				
				userChannels.add(t.toJson());
				
			}
			
			
		});
				
		return userChannels;
		
	}
	
	public List<String> getAvailableDataTypesForUser(String userId){
		
		final List<String> userTypes = new ArrayList<String>();		
		
		FindIterable<Document> iterable = db.getCollection(COLLECTION_NAME).find(new Document("user_id", userId)).projection(Projections.fields(Projections.include("type_name"), Projections.exclude("_id" /*, "user_id"*/)));				
		
		iterable.forEach(new Block<Document>(){

			@Override
			public void apply(Document t) {				
				userTypes.add(t.getString("type_name"));
				
			}
			
			
		});
		
		List<String> types = new Consts().getTypes();
		
		for(String type : userTypes){
			types.add(type);
		}
		
		return types;
	}
	
	public void createChannel(String userId, Map<String, Object> channel){
		channel.put("user_id", userId);
		db.getCollection(COLLECTION_NAME).insertOne(new Document(channel));
	}
}
