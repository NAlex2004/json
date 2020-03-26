package com.alex.json.elements;

import com.alex.json.interfaces.JsonStringHelper;
import com.alex.json.interfaces.JsonValue;

public class JsonInteger implements JsonValue {
    private final int value;

    public JsonInteger(int value) {
        this.value = value;
    }

    @Override
    public String[] getLines(JsonStringHelper helper) {
        return helper.getLines(this);
    }

    @Override
    public Object getValue() {
        return value;
    }
    
}