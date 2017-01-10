package json;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private ArrayList<JsonPair> jsonPairs;

    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = new ArrayList();
        for (JsonPair jPair : jsonPairs) {
            add(jPair);
        }
    }

    @Override
    public String toJson() {
        StringBuilder jsonStr = new StringBuilder();
        int i = 0;
        jsonStr.append("{\n");
        for (JsonPair jPair : jsonPairs) {
            jsonStr.append(jPair.key)
                    .append(":")
                    .append(jPair.value.toJson());
            if (i < jsonPairs.size() - 1) {
                jsonStr.append(",\n");
            }
            i++;
        }
        jsonStr.append("}");
        return jsonStr.toString();
    }

    private int checkForReplacement(JsonPair jsonPair) {
        int i = 0;
        for (JsonPair jPair : jsonPairs) {
            if (jPair.key.equals(jsonPair.key)) return i;
            i++;
        }
        return -1;
    }

    private void replaceElem(int index, JsonPair jsonPair) {
        jsonPairs.set(index, jsonPair);
    }

    public void add(JsonPair jsonPair) {
        int i = checkForReplacement(jsonPair);
        if (i != -1) {
            replaceElem(i, jsonPair);
        } else {
            jsonPairs.add(jsonPair);
        }
    }


    public Json find(String name) {
        for (JsonPair jPair : jsonPairs) {
            if (jPair.key.equals(name)) {
                return jPair.value;
            }
        }
        return null;
    }

    public boolean contains(String name){
        return find(name) != null;
    }

    public JsonObject projection(String... names) {
        JsonObject newJsonObject = new JsonObject();
        for(String name : names){
            for(JsonPair jPair: jsonPairs){
                if(name.equals(jPair.key)){
                    newJsonObject.add(jPair);
                }
            }
        }
        return newJsonObject;
    }
}
