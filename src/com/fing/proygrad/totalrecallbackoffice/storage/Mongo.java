package com.fing.proygrad.totalrecallbackoffice.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

public class Mongo {

	private static final String CONN_STRING = "mongodb://localhost:27017";
	private static final String DB_NAME = "total_recall";
	private static final String COLLECTION_NAME = "data_types";
	
	MongoClient client;
	MongoDatabase db;
	
	public Mongo(){
		
		client = new MongoClient((new MongoClientURI(CONN_STRING)));
		db = client.getDatabase(DB_NAME);
		
		
	}
	
	
	public void insert(Map<String, Object> data){
		
		db.getCollection(COLLECTION_NAME).insertOne(new Document(data));
		
	}
	
	public List<String> listUserTypes(String userId){
		
		List<String> userTypes = new ArrayList<String>();
		
		//Map<String, Object> wantedFields = new HashMap<String, Object>();
		
		BasicDBObject includeKeys = new BasicDBObject();
		
		FindIterable<Document> docs = db.getCollection(COLLECTION_NAME).find(new Document("user_id", userId)).projection(Projections.fields(Projections.exclude("_id" , "user_id")));
		
		Document doc;
		
		if(docs != null){
			while(docs.iterator().hasNext()){
				doc = docs.iterator().next();
				userTypes.add(doc.toJson());
			}
		}
		
		return userTypes;
	}
	
	
	
	
	
}
