package com.alex.json.parser;

import com.alex.json.interfaces.Json;

public interface JsonParser {
	Json parse() throws InvalidJsonException;
}
