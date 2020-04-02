package com.alex.json.parser;

import java.util.ArrayList;
import java.util.List;

import com.alex.json.elements.JsonArray;
import com.alex.json.elements.JsonBoolean;
import com.alex.json.elements.JsonDouble;
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
    private static final char KEY_QUOTA = '"';
    private static final char ESCAPE_CHAR = '\\';
    private static final char KEY_VALUE_DELIMETER = ':';

    private boolean isValidNumberChar(char ch) {
		return (ch == '-' 
				|| ch == '+' 
				|| ch == '.' 
				|| Character.isDigit(ch) 
				|| Character.toLowerCase(ch) == 'e');
	}
    
    private JsonValue readNumber() throws InvalidJsonException {    	
    	int startIndex = index;
    	while (index < sourceLength && isValidNumberChar(source.charAt(index))) {
			index++;
		}
    	String str = source.substring(startIndex, index);
    	try {
        	double number = Double.parseDouble(str);			
        	
        	if (!Double.isNaN(number) && !Double.isInfinite(number)) {
        		if (Math.floor(number) == number) {
        			return new JsonInteger((long)number);
        		}
        		
        		return new JsonDouble(number);
        	}
		} catch (NumberFormatException e) {			
		}

    	throw new InvalidJsonException("Not a valid number.", startIndex);
    }

    /**
     * Reads string value. index must be after open quotas.
     */
    private String readStringInQuotas() {
    	int startIndex = index;        
        boolean hasEscapeChar = false;
        loop: while (index < sourceLength) {
            switch (source.charAt(index)) {
                case KEY_QUOTA:
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
                
        return source.substring(startIndex, index - 1);
	}
    
    private JsonString readString() throws InvalidJsonException {
        if (source.charAt(index) != KEY_QUOTA) {
            throw new InvalidJsonException("Missing string quotas.", index);
        }
        
        index++;
        String str = readStringInQuotas();        
        return new JsonString(str);
    }

    private JsonBoolean readBoolean() throws InvalidJsonException {
    	int startIndex = index;
    	while (index < sourceLength && Character.isLetter(source.charAt(index))) {			
    		index++;
		}
    	String str = source.substring(startIndex, index).toLowerCase();
    	if (str.equalsIgnoreCase("true")) {
    		return new JsonBoolean(true);
    	} else if (str.equalsIgnoreCase("false")) {
			return new JsonBoolean(false);
		}
    	
        throw new InvalidJsonException("Not a valid boolean value.", startIndex);
    }

    private JsonNull readNull() throws InvalidJsonException {
    	int startIndex = index;
    	while (index < sourceLength && Character.isLetter(source.charAt(index))) {			
    		index++;
		}
    	String str = source.substring(startIndex, index).toLowerCase();
    	if (str.equalsIgnoreCase("null")) {
    		return new JsonNull();
    	}
    	
    	throw new InvalidJsonException("Not a valid null value", startIndex);
    }

    private JsonArray readArray() throws InvalidJsonException {
    	if (source.charAt(index) != ARRAY_OPEN_BRACKET) {
    		throw new InvalidJsonException("No array open bracket found.", index);
    	}
    	index++;
    	skipSpaces();
    	List<JsonValue> values = new ArrayList<>();
    	boolean lastCharWasComma = false;
        while (index < sourceLength) {
            if (source.charAt(index) == ARRAY_CLOSE_BRACKET) {
                break;
            }
            
            lastCharWasComma = false;            
            JsonValue value = readValue();
            values.add(value);

            skipSpaces();
            if (index >= sourceLength) {
                throw new InvalidJsonException("Array close bracket is absent.", index);
            }
            
            lastCharWasComma = source.charAt(index) == ',';
            if (lastCharWasComma) {
                index++;
            } else if (source.charAt(index) != ARRAY_CLOSE_BRACKET) {
				throw new InvalidJsonException("Missing comma between array entries.", index);
			}
        }
        
        if (lastCharWasComma) {
            throw new InvalidJsonException("Missing array entry after comma", index);
        }
        index++;
    	return new JsonArray(values.toArray(new JsonValue[0]));
    }

    private JsonValue readValue() throws InvalidJsonException {
    	skipSpaces();
    	if (index >= sourceLength) {
    		throw new InvalidJsonException("No value found.", index);
    	}
    	char currentChar = source.charAt(index);
    	JsonValue value = null;
    	if (Character.isDigit(currentChar) || currentChar == '-' || currentChar == '+') {
    		value = readNumber();
    	} else if (currentChar == KEY_QUOTA) {
    		value = readString();
    	} else if (currentChar == ARRAY_OPEN_BRACKET) {
			value = readArray();
		} else if (currentChar == OBJECT_OPEN_BRACKET) {
			value = readObject();
		} else if (Character.toLowerCase(currentChar) == 'n') {
			value = readNull();
		} else if (Character.toLowerCase(currentChar) == 'f' || Character.toLowerCase(currentChar) == 't') {
			value = readBoolean();
		} else {
			throw new InvalidJsonException("Not a valid value", index);
		}
    	return value;
    }

    private String readKey() throws InvalidJsonException {
        skipSpaces();
        if (source.charAt(index) != KEY_QUOTA) {
            throw new InvalidJsonException("Object key does not have quota/quotas.", index);
        }
        
        index++;
        String str = readStringInQuotas();
        
        if (str.isEmpty()) {
            throw new InvalidJsonException("Empty object key.", index);
        }
        
        return str;
    }

    private void skipSpaces() {
        while(index < sourceLength && Character.isWhitespace(source.charAt(index))) {
            index++;
        }
    }

    private void readKeyValueDelimeter() throws InvalidJsonException {
        skipSpaces();
        if (source.charAt(index) != KEY_VALUE_DELIMETER) {
            throw new InvalidJsonException("Key-value delimeter (':')not found.", index);
        }
        index++;
        skipSpaces();
    }

    private JsonObject readObject() throws InvalidJsonException {
        List<JsonElement> elements = new ArrayList<>();
        skipSpaces();
        if (source.charAt(index) != OBJECT_OPEN_BRACKET) {
            throw new InvalidJsonException("Object open bracket is absent.", index);
        }
        index++;
        skipSpaces();
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
                throw new InvalidJsonException("Object close bracket is absent.", index);
            }
            lastCharWasComma = source.charAt(index) == ',';
            if (lastCharWasComma) {
                index++;
            } else if (source.charAt(index) != OBJECT_CLOSE_BRACKET) {
				throw new InvalidJsonException("Missing comma between object entries.", index);
			}
        }
        
        if (lastCharWasComma) {
            throw new InvalidJsonException("Missing key-value entry after comma", index);
        }
        index++;
        return new JsonObject(elements.toArray(new JsonElement[0]));
    }

    public JsonStringParser(String jsonString) {        
        source = jsonString.trim();
    }

    public String getSource() {
    	return source;
    }
    
    public void setSource(String source) {
        if (source != null) {
            this.source = source.trim();
        }
    }

    @Override
    public Json parse() throws InvalidJsonException {
        if (source == null || source.isEmpty()) {
             throw new InvalidJsonException("Source is null or empty.", index);
        }
        
        index = 0;
        sourceLength = source.length();
        Json object = readObject();
        skipSpaces();
        if (index < sourceLength) {
        	throw new InvalidJsonException("Some chars exists after end of initial object. Only one main object is supported.");
        }
        
        return object;
    }
    
}