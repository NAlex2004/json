package app.com.alex.json.elements;

import java.util.ArrayList;
import java.util.List;

public class JsonObject implements Json {
    private JsonElement[] elements;

    public JsonObject(JsonElement[] elements) {
        this.elements = elements != null ? elements : new JsonElement[0];
    }

    @Override
    public JsonElement[] getElements() {
        return elements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str: getLines()) {
            sb.append(str);
        }

        return sb.toString();
    }

    @Override
    public String[] getLines() {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();        
        lines.add("{");

        // may be we can just use string here..
        for (JsonElement element : elements) {
            sb.append('\t')
                .append(element.getKey()).append(" : ");
            for (String line : element.getValueLines()) {
                sb.append(line);
                lines.add(sb.toString());
                sb.setLength(0);
                sb.append("\t");
            }                
        }
        lines.add("}");
        
        return lines.toArray(new String[0]);
    }
}