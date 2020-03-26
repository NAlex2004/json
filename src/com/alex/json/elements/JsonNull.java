package com.alex.json.elements;

import com.alex.json.interfaces.JsonStringHelper;
import com.alex.json.interfaces.JsonValue;

public class JsonNull implements JsonValue {

	@Override
	public String[] getLines(JsonStringHelper helper) {
		return helper.getLines(this);
	}

	@Override
	public Object getValue() {
		return "null";
	}
    
}