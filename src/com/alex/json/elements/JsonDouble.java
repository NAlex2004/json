package com.alex.json.elements;

public class JsonDouble implements JsonValue {
    private final double value;

    public JsonDouble(double value) {
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