package com.fing.proygrad.totalrecallbackoffice.util;

import java.util.ArrayList;
import java.util.List;

public final class Consts {

	public static final String STRING = "String";
	public static final String FLOAT = "Float";
	public static final String INT = "Integer";
	public static final String DOUBLE = "Double";
	public static final String BYTE = "Byte";

	public List<String> getTypes(){
		List<String> types = new ArrayList<String>();
		
		types.add(STRING);
		types.add(FLOAT);
		types.add(INT);
		types.add(DOUBLE);
		types.add(BYTE);
		
		return types;
	}
	
	
}
