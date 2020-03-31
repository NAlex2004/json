package com.alex.json.elements;

import com.alex.json.interfaces.JsonStringHelper;
import com.alex.json.interfaces.JsonValue;

public class JsonString implements JsonValue {
    private final String value;

    public JsonString(String value) {
    	if (value == null) {
    		throw new IllegalArgumentException("Value cannot be null.");
    	}
        this.value = value;
    }

    @Override
    public String[] getLines(JsonStringHelper helper) {
        return helper.getLines(this);
    }

    @Override
    public Object getValue() {
        return value;
    }
    
}