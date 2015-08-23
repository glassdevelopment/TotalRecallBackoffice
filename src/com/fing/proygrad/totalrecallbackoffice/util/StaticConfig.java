package com.fing.proygrad.totalrecallbackoffice.util;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

public final class StaticConfig {

	private static Properties prop;
	private static String CONF_PATH = "/conf.properties";
	private static String MESSAGES_PATH = "/messages_";
	private static boolean isInit = false;
	
	private StaticConfig(){
		
		
	}
	
	public static String getValue(String key){	
		if(!isInit){
			init();
		}
		return prop.getProperty(key);
	}
	
	public static String getMessage(String key, String...params){
		if(!isInit){
			init();
		}
		return MessageFormat.format(getValue(key), params);
	}
	
	public static String getMessage(String key){
		if(!isInit){
			init();
		}else{
		}
		return getValue(key);
	}
	
	public static int getIntValue(String key){
		if(!isInit){
			init();
		}
		return Integer.valueOf(prop.getProperty(key));
	}
	
	private static void init(){
		try{
			prop = new Properties();
			
			InputStream conf = StaticConfig.class.getResourceAsStream(CONF_PATH);
			
			prop.load(conf);	
			String lang = prop.getProperty("language");
			InputStream messages = StaticConfig.class.getResourceAsStream(MESSAGES_PATH + lang + ".properties");		
			prop.load(messages);	
			isInit = true;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
