package com.alex.json.helpers;

import java.util.ArrayList;
import java.util.List;

import com.alex.json.elements.JsonArray;
import com.alex.json.elements.JsonElement;
import com.alex.json.elements.JsonObject;
import com.alex.json.elements.JsonString;
import com.alex.json.interfaces.JsonStringHelper;
import com.alex.json.interfaces.JsonValue;

public class JsonStringHelperImpl implements JsonStringHelper {
	
	@Override
	public String[] getLines(JsonValue value) {
		return new String[] { value.getValue().toString() };
	}
	
	@Override
	public String[] getLines(JsonString stringValue) {
		return new String[] { "\"" + stringValue.getValue() + "\"" };
	}

	@Override
	public String[] getLines(JsonArray arrayValue) {
		JsonValue[] items = arrayValue.getValues();
		List<String> lines = new ArrayList<>();
		StringBuilder sb = new StringBuilder();        
		sb.append("[ ");

		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				String[] itemLines = items[i].getLines(this);
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
	public String[] getLines(JsonObject objectValue) {
		JsonElement[] elements = objectValue.getElements();
		List<String> lines = new ArrayList<>();
		StringBuilder sb = new StringBuilder();        
		lines.add("{");

		for (JsonElement element : elements) {
			sb.append('\t')
			.append('"').append(element.getKey()).append('"').append(" : ");

			String[] valueLines = element.getJsonValue().getLines(this);
			for (int i = 0; i < valueLines.length; i++) {                
				sb.append(valueLines[i]);
				if (i < valueLines.length - 1) {
					sb.append(", ");
				}
				lines.add(sb.toString());
				sb.setLength(0);
				sb.append("\t");
			}                
		}

		lines.add("}");

		return lines.toArray(new String[0]);
	}

	@Override
	public String toJsonString(JsonObject value) {
		StringBuilder sb = new StringBuilder();
		String[] lines = value.getLines(this);
		for (String str: lines) {
			sb.append(str).append(System.lineSeparator());
		}

		return sb.toString();
	}
}
