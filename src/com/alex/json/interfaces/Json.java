package com.alex.json.interfaces;

import com.alex.json.elements.JsonElement;

public interface Json {
	public JsonElement[] getElements();
	public String[] getLines(JsonStringHelper helper);
	public String toJsonString(JsonStringHelper helper);
}