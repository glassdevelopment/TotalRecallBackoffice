package com.fing.proygrad.totalrecallbackoffice.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

public class Config {

	private Properties prop;
	private static String CONF_PATH = "/conf.properties";
	private static String MESSAGES_PATH = "/messages_";
	
	public Config() throws IOException {
		
		prop = new Properties();
		
		InputStream conf = this.getClass().getResourceAsStream(CONF_PATH);
		
		prop.load(conf);	
		String lang = prop.getProperty("language");
		InputStream messages = this.getClass().getResourceAsStream(MESSAGES_PATH + lang + ".properties");		
		prop.load(messages);	
	}
	
	public String getValue(String key){	
		return prop.getProperty(key);
	}
	
	public String getMessage(String key, String...params){	
		return MessageFormat.format(getValue(key), params);
	}
	
	public String getMessage(String key){
		return getValue(key);
	}
	
	public int getIntValue(String key){
		return Integer.valueOf(prop.getProperty(key));
	}

}
