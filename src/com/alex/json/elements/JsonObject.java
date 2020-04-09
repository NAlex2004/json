package com.alex.json.elements;

import java.util.Arrays;

import com.alex.json.interfaces.JsonComplexValue;
import com.alex.json.interfaces.JsonStringHelper;
import com.alex.json.interfaces.JsonValue;

public class JsonObject implements JsonComplexValue {
	private JsonElement[] elements;

	public JsonObject(JsonElement[] elements) {
		this.elements = elements != null ? elements : new JsonElement[0];
	}	

	@Override
	public String toJsonString(JsonStringHelper helper) {
		return helper.toJsonString(this);
	}

	@Override
	public String[] getLines(JsonStringHelper helper) {
		return helper.getLines(this);
	}

	@Override
	public JsonValue[] getValues() {
		return Arrays.stream(elements).map(el -> el.getJsonValue()).toArray(JsonValue[]::new);
	}

	@Override
	public Object getValue() {		
		return elements;
	}
	
	public JsonElement[] getElements() {
		return elements;
	}
}