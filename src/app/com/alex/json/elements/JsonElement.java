package app.com.alex.json.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonElement {
    private String key;
    private Object value;

    public JsonElement(String key, Object value) {
        // ToDo: may be check key for null?
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public List<String> getValueLines() {
        return getValueLines(0);
    }

    public List<String> getValueLines(int tabCount) {
        tabCount = Math.max(0, tabCount);                
        char[] tabs = new char[tabCount];
        Arrays.fill(tabs, '\t');
        
        List<String> lines = new ArrayList<>();
        // ToDo: create lines if value is json object or array
        
        return lines;
    }
}