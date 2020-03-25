package com.alex.json.helpers;

import java.util.ArrayList;
import java.util.List;

import com.alex.json.interfaces.JsonComplexValue;
import com.alex.json.interfaces.JsonValue;

public class JsonComplexValueHelper {
	/**
	 * Get multiline JSON string for array or object     
	 */
	public static String[] getValueLines(JsonComplexValue value, boolean oneLinePerValue) {
		JsonValue[] values = value.getValues();
		StringBuilder sb = new StringBuilder();
		List<String> lines = new ArrayList<>();

		for (int valIndex = 0; valIndex < values.length; valIndex ++) {
			String[] valueLines = values[valIndex].getLines();
			if (valueLines == null || valueLines.length == 0) {
				continue;
			}

			sb.append(valueLines[0]);

			if (valueLines.length > 1) {    			
				lines.add(sb.toString());
				sb.setLength(0);
				sb.append('\t');

				for (int i = 1; i < valueLines.length; i++) {                
					lines.add("\t" + valueLines[i]);                
				}	
			}

			if (valIndex < values.length - 1) {
				sb.append(", ");
			}

			if (oneLinePerValue) {
				lines.add(sb.toString());
				sb.setLength(0);                    
			}                 		    		
		}

		lines.add(sb.toString());

		return lines.toArray(new String[0]);
	}
}