package app;

import java.util.ArrayList;
import java.util.List;

import com.alex.json.elements.*;
import com.alex.json.helpers.JsonStringHelperImpl;
import com.alex.json.interfaces.*;

public class App {
    
	public static void main(String[] args) throws Exception {
        List<JsonValue> values = new ArrayList<JsonValue>();
        List<JsonElement> elements = new ArrayList<JsonElement>();
        JsonValue value = new JsonInteger(123);
        values.add(new JsonDouble(153.24));
        values.add(new JsonNull());
        values.add(new JsonString("shit"));
        values.add(new JsonBoolean(true));
        JsonArray array1 = new JsonArray(values.toArray(new JsonValue[0]));
        JsonArray array2 = new JsonArray(new JsonValue[] {
        		new JsonInteger(55),
        		new JsonNull(),
        		new JsonString("shit")
        });
        
        elements.add(new JsonElement("string", new JsonString("string value")));
        elements.add(new JsonElement("int", new JsonInteger(113)));
        elements.add(new JsonElement("double", new JsonDouble(54.874)));
        elements.add(new JsonElement("null", new JsonNull()));
        elements.add(new JsonElement("boolean", new JsonBoolean(true)));
        elements.add(new JsonElement("array", array1));
        
        JsonObject object1 = new JsonObject(elements.toArray(new JsonElement[0]));
        elements.clear();
        
        elements.add(new JsonElement("string", new JsonString("another string value")));
        elements.add(new JsonElement("int", new JsonInteger(1)));
        elements.add(new JsonElement("double", new JsonDouble(5.8)));
        elements.add(new JsonElement("boolean", new JsonBoolean(true)));        
        elements.add(new JsonElement("array", array2));
                
        elements.add(new JsonElement("object", object1));
        
        JsonObject object2 = new JsonObject(elements.toArray(new JsonElement[0]));
        
        String json = object2.toJsonString(new JsonStringHelperImpl());
        
        System.out.println(json);
    }
}