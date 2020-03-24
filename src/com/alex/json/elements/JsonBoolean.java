package com.alex.json.elements;

public class JsonBoolean implements JsonValue {
    private final boolean value;

    public JsonBoolean(boolean value) {
        this.value = value;
    }
    
    @Override
    public String[] getLines() {
        return new String[] { String.valueOf(value) };
    }

    @Override
    public Object getValue() {
        return value;
    }
    
}