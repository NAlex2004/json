package com.alex.json.parser;

import java.util.ArrayList;
import java.util.List;

import com.alex.json.elements.JsonArray;
import com.alex.json.elements.JsonBoolean;
import com.alex.json.elements.JsonElement;
import com.alex.json.elements.JsonInteger;
import com.alex.json.elements.JsonNull;
import com.alex.json.elements.JsonObject;
import com.alex.json.elements.JsonString;
import com.alex.json.interfaces.Json;
import com.alex.json.interfaces.JsonValue;

public class JsonStringParser implements JsonParser {
    private String source;
    private int index = 0;
    private int sourceLength = 0;
    private static final char OBJECT_OPEN_BRACKET = '{';
    private static final char OBJECT_CLOSE_BRACKET = '}';
    private static final char ARRAY_OPEN_BRACKET = '[';
    private static final char ARRAY_CLOSE_BRACKET = ']';
    private static final char KEY_BRACKET = '"';
    private static final char ESCAPE_CHAR = '\\';
    private static final char KEY_VALUE_DELIMETER = ':';

    private JsonValue readNumber() {
        return null;
    }

    private JsonString readString() {
        return null;
    }

    private JsonBoolean readBoolean() {
        return null;
    }

    private JsonNull readNull() {
        return null;
    }

    private JsonArray readArray() {
        return null;
    }

    private JsonValue readValue() {
        return null;
    }

    private String readKey() throws InvalidJsonException {
        skipSpaces();
        if (source.charAt(index) != KEY_BRACKET) {
            throw new InvalidJsonException("Object key does not have quota/quotas.");
        }
        
        index++;
        int startIndex = index;        
        boolean hasEscapeChar = false;
        loop: while (index < sourceLength) {
            switch (source.charAt(index)) {
                case KEY_BRACKET:
                    // skip quotas after escape char
                    if (hasEscapeChar) {
                        hasEscapeChar = false;
                    } else {
                        index++;
                        break loop;
                    }
                    break;
                case ESCAPE_CHAR:
                    hasEscapeChar = !hasEscapeChar;
                    break;
                default:
                    hasEscapeChar = false;
                    break;
            }

            index++;
        }
        
        if (startIndex == index - 1) {
            throw new InvalidJsonException("Empty object key.");
        }
        
        return source.substring(startIndex, index - 1);
    }

    private void skipSpaces() {
        while(index < sourceLength && Character.isWhitespace(source.charAt(index))) {
            index++;
        }
    }

    private void readKeyValueDelimeter() throws InvalidJsonException {
        skipSpaces();
        if (source.charAt(index) != KEY_VALUE_DELIMETER) {
            throw new InvalidJsonException("Key-value delimeter (':')not found.");
        }
        index++;
        skipSpaces();
    }

    private Json readObject() throws InvalidJsonException {
        List<JsonElement> elements = new ArrayList<>();
        skipSpaces();
        if (source.charAt(index) != OBJECT_OPEN_BRACKET) {
            throw new InvalidJsonException("Object open bracket is absent.");
        }
        index++;
        boolean lastCharWasComma = false;
        while (index < sourceLength) {
            if (source.charAt(index) == OBJECT_CLOSE_BRACKET) {
                break;
            }
            
            lastCharWasComma = false;
            String key = readKey();
            readKeyValueDelimeter();
            JsonValue value = readValue();

            JsonElement element = new JsonElement(key, value);
            elements.add(element);
            
            // comma or end of object
            skipSpaces();
            if (index >= sourceLength) {
                throw new InvalidJsonException("Object close bracket is absent.");
            }
            lastCharWasComma = source.charAt(index) == ',';
            if (lastCharWasComma) {
                index++;
            }
        }
        if (lastCharWasComma) {
            throw new InvalidJsonException("Missing key-value entry after comma");
        }
        return new JsonObject(elements.toArray(new JsonElement[0]));
    }

    public JsonStringParser(String jsonString) {        
        source = jsonString.trim();
    }

    public void setSource(String source) {
        if (source != null) {
            this.source = source.trim();
        }
    }

    @Override
    public Json parse() throws InvalidJsonException {
        if (source == null || source.isEmpty()) {
             throw new InvalidJsonException("Source is null or empty.");
        }
        
        index = 0;
        sourceLength = source.length();
        return readObject();
    }
    
}