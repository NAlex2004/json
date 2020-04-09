package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alex.json.elements.*;
import com.alex.json.helpers.JsonStringHelperImpl;
import com.alex.json.interfaces.*;
import com.alex.json.parser.InvalidJsonException;
import com.alex.json.parser.JsonParser;
import com.alex.json.parser.JsonStringParser;

public class App {
    static void testJsonOutput() {
        List<JsonValue> values = new ArrayList<JsonValue>();
        List<JsonElement> elements = new ArrayList<JsonElement>();        
        values.add(new JsonDouble(153.24));
        values.add(new JsonNull());
        values.add(new JsonString("text"));
        values.add(new JsonBoolean(true));
        JsonArray array1 = new JsonArray(values.toArray(new JsonValue[0]));
        
        
        elements.add(new JsonElement("string", new JsonString("string value")));
        elements.add(new JsonElement("int", new JsonInteger(113)));
        elements.add(new JsonElement("double", new JsonDouble(54.874)));
        elements.add(new JsonElement("null", new JsonNull()));
        elements.add(new JsonElement("boolean", new JsonBoolean(true)));
//        elements.add(new JsonElement("array", array1));
        
        JsonObject object1 = new JsonObject(elements.toArray(new JsonElement[0]));
        elements.clear();
        
        JsonArray array2 = new JsonArray(new JsonValue[] {
        		new JsonInteger(55),
        		new JsonNull(),
        		object1,
        		new JsonString("text"),   
        		array1,
        		object1
        });
        
        elements.add(new JsonElement("string", new JsonString("another string value")));
        elements.add(new JsonElement("int", new JsonInteger(1)));
        elements.add(new JsonElement("double", new JsonDouble(5.8)));
        elements.add(new JsonElement("boolean", new JsonBoolean(true)));        
        elements.add(new JsonElement("array", array2));

        JsonObject object2 = new JsonObject(elements.toArray(new JsonElement[0]));
        
        String json = object2.toJsonString(new JsonStringHelperImpl());
        
        System.out.println(json);
     	
    }
    
    static String fileToString(String path) {    	    		  	
    	try {								
			File file = new File(path);
			List<String> lines = Files.readAllLines(file.toPath());
			String str = lines.stream().collect(Collectors.joining(System.lineSeparator()));
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return "";
    }
    
	public static void main(String[] args) throws Exception {
//		testJsonOutput();
		String str = fileToString("src/app/file.json");
		JsonParser parser = new JsonStringParser(str);
		try {
			JsonValue json = parser.parse();	
			JsonStringHelper stringHelper = new JsonStringHelperImpl();
			System.out.println(json.toJsonString(stringHelper));
		} catch (InvalidJsonException e) {
			System.out.println("ERROR: " + e.getMessage() + " at " + e.getIndex());
		}				
	}
}