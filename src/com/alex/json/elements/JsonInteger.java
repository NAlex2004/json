package com.alex.json.elements;

public class JsonInteger implements JsonValue {
    private final int value;

    public JsonInteger(int value) {
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