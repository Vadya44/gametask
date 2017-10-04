package vadyaprod.gametask;

import android.support.annotation.Nullable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import vadyaprod.gametask.model.Data;

/**
 * Created by Vadya on 02.10.17.
 */

public class InfoDeserializer implements JsonDeserializer<Data> {
    @Nullable
    public Data deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context) throws JsonParseException {
        Data data = null;
        if (json.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> entries =
                    json.getAsJsonObject().entrySet();
            if (entries.size() > 0) {
                Map.Entry<String, JsonElement> entry = entries.iterator().next();
                data = new Data(entry.getValue().getAsString());
            }
            final JsonObject jsonObject = json.getAsJsonObject();
            final JsonElement timeStamp = jsonObject.get("timestamp");
            data = new Data(timeStamp.getAsString());
        }
        return data;
    }
}
