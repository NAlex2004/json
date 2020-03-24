package com.alex.json.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonElement {
    private String key;
    private JsonValue value;

    public JsonElement(String key, JsonValue value) {
        // ToDo: may be check key for null?
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public JsonValue getJsonValue() {
        return value;
    }

    public String[] getLines() {
        // tabCount = Math.max(0, tabCount);                
        // char[] tabs = new char[tabCount];
        // Arrays.fill(tabs, '\t');
        
        List<String> lines = new ArrayList<>();
        // ToDo: create lines if value is json object or array
        
        return lines.toArray(new String[0]);
    }
}