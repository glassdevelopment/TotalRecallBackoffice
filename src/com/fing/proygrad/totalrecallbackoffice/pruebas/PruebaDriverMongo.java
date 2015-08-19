package com.fing.proygrad.totalrecallbackoffice.pruebas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bson.Document;







import com.fing.proygrad.totalrecallbackoffice.storage.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class PruebaDriverMongo {

	private static String CONN_STRING = "mongodb://localhost:27017";
	
	public static void main(String[] args) {
		
		//createDocuments();
		for(String doc : getDocs("1")){
			System.out.println("documento: " + doc);
		}
	}
	
	static void simple(){
		MongoClient client = new MongoClient((new MongoClientURI(CONN_STRING)));
		MongoDatabase database = client.getDatabase("prueba");

		database.getCollection("col").insertOne(new Document("dsfsd", "sdfsd"));
	}
	
	static void createDocuments(){
		Map<String, Object> doc;
		Mongo mongo = new Mongo();
		
		for(int i = 0; i < 20; i++){
			doc = new HashMap<String, Object>();
			doc.put("user_id", String.valueOf(i%2));
			doc.put("val", "val_" + i);
			mongo.insert(doc);
		}
	}
	
	static List<String> getDocs(String userId){
		return new Mongo().listUserTypes(userId);
	}
	
}
