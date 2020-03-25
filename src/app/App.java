package app;

import java.util.ArrayList;
import java.util.List;

import com.alex.json.elements.*;
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
        
        
    }
}