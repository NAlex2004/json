package com.alex.json.elements;

import com.alex.json.interfaces.JsonStringHelper;
import com.alex.json.interfaces.JsonValue;

public class JsonDouble implements JsonValue {
    private final double value;

    public JsonDouble(double value) {
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

	@Override
	public String toJsonString(JsonStringHelper helper) {
		return helper.toJsonString(this);
	}
    
}