package com.alex.json.elements;

import com.alex.json.interfaces.JsonValue;

public class JsonElement {
	private String key;
	private JsonValue value;

	public JsonElement(String key, JsonValue value) {
		// TODO: may be check key for null?
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public JsonValue getJsonValue() {
		return value;
	}
}