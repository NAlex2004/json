package com.alex.json.interfaces;

import com.alex.json.elements.JsonElement;

public interface Json extends Multiline {
	public JsonElement[] getElements();	
	public String toJsonString(JsonStringHelper helper);
}