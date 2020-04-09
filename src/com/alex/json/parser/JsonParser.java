package com.alex.json.parser;

import com.alex.json.interfaces.JsonValue;

public interface JsonParser {
	JsonValue parse() throws InvalidJsonException;
}
