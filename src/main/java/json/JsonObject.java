package json;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    Map<String, Json> map;

    public JsonObject(JsonPair... jsonPairs) {
        map = new HashMap<String, Json>();
        for (JsonPair pair: jsonPairs) {
            map.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuffer str = new StringBuffer("{");
        for (String key: map.keySet()) {
            str.append("'");
            str.append(key);
            str.append("'");
            str.append(": ");
            str.append(map.get(key).toJson());
            str.append(", ");
        }
        str.delete(str.length()-2, str.length());
        str.append("}");
        return str.toString();
    }

    public void add(JsonPair jsonPair) {
        map.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        for (String key: map.keySet()) {
            if (key.equals(name)) {
                return map.get(key);
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject proj = new JsonObject();
        for (String name: names) {
            if (map.keySet().contains(name)) {
                proj.add(new JsonPair(name, map.get(name)));
            }
        }
        return proj;
    }
}
