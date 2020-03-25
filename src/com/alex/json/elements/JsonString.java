package com.alex.json.elements;

import com.alex.json.interfaces.JsonValue;

public class JsonString implements JsonValue {
    private final String value;

    public JsonString(String value) {
        this.value = value;
    }

    @Override
    public String[] getLines() {
        return new String[] { "\"value\"" };
    }

    @Override
    public Object getValue() {
        return value;
    }
    
}