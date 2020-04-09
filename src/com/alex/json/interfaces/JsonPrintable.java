package com.alex.json.interfaces;

public interface JsonPrintable {
	String[] getLines(JsonStringHelper helper);
	String toJsonString(JsonStringHelper helper);
}