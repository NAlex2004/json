package com.alex.json.elements;

import com.alex.json.interfaces.JsonComplexValue;
import com.alex.json.interfaces.JsonStringHelper;
import com.alex.json.interfaces.JsonValue;

public class JsonArray implements JsonComplexValue {
	private JsonValue[] items;

	public JsonArray(JsonValue[] items) {
		if (items == null) {
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		this.items = items;
	}

	@Override
	public JsonValue[] getValues() {
		return items;
	}

	@Override
	public Object getValue() {
		return items;
	}

	@Override
	public String[] getLines(JsonStringHelper helper) {
		return helper.getLines(this);
	}

	@Override
	public String toJsonString(JsonStringHelper helper) {	
		return helper.toJsonString(this);
	}

}
