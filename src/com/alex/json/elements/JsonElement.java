package com.alex.json.elements;

import com.alex.json.interfaces.JsonValue;

public class JsonElement {
	private String key;
	private JsonValue value;

	public JsonElement(String key, JsonValue value) {
		if (key == null) {
			throw new IllegalArgumentException("Key cannot be null.");
		}
		if (value == null) {
			throw new IllegalArgumentException("Value cannot be null.");
		}
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