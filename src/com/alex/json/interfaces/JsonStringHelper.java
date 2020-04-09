package com.alex.json.interfaces;

import com.alex.json.elements.JsonArray;
import com.alex.json.elements.JsonObject;
import com.alex.json.elements.JsonString;

public interface JsonStringHelper {
	String[] getLines(JsonValue value);
	String[] getLines(JsonString stringValue);
	String[] getLines(JsonArray arrayValue);
	String[] getLines(JsonObject objectValue);
	
	String toJsonString(JsonValue value);
	String toJsonString(JsonString value);
	String toJsonString(JsonArray value);
	String toJsonString(JsonObject value);
}
