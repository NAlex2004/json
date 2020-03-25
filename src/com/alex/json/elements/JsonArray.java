package com.alex.json.elements;

import java.util.ArrayList;
import java.util.List;

import com.alex.json.interfaces.JsonComplexValue;
import com.alex.json.interfaces.JsonValue;

public class JsonArray implements JsonValue, JsonComplexValue {
	private JsonValue[] items;
	
	public JsonArray(JsonValue[] items) {
		this.items = items;
	}

	@Override
	public JsonValue[] getValues() {
		return items;
	}

	@Override
	public Object getValue() {
		return items;
	}

	@Override
	public String[] getLines() {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();        
        sb.append("[ ");

        if (items != null) {
        	for (int i = 0; i < items.length; i++) {
            	String[] itemLines = items[i].getLines();
            	if (itemLines == null) {
            		sb.append("null");
            	} else {
            		sb.append(itemLines[0]);
            		if (itemLines.length > 1) {
            			lines.add(sb.toString());
            			sb.setLength(0);
            			for (int lineIndex = 1; lineIndex < itemLines.length - 1; lineIndex++) {
            				lines.add("\t" + itemLines[lineIndex]);
            			}
            			sb.append('\t').append(itemLines[itemLines.length - 1]);            			
            		}
            	}
            	
            	if (i < items.length - 1) {
            		sb.append(", ");
            	}
            }	
        }
        
        sb.append(" ]");
        lines.add(sb.toString());
        
        return lines.toArray(new String[0]);
	}
	
	@Override
	public String toString() {
		String[] lines = getLines();
		StringBuilder sb = new StringBuilder();
		for (String string : lines) {
			sb.append(string).append(System.lineSeparator());
		}
		
		return sb.toString();
	}
}
