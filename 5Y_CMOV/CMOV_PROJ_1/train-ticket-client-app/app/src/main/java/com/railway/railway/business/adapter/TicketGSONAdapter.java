package com.railway.railway.business.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.railway.railway.business.api.entity.Ticket;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by cteixeira on 31-10-2015.
 * com.railway.railway.business.adapter
 */
public class TicketGSONAdapter  implements JsonSerializer<Ticket>, JsonDeserializer<Ticket> {

    @Override
    public JsonElement serialize(Ticket src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("id"            ,src.getId());
        obj.addProperty("arrival"       ,src.getArrival());
        obj.addProperty("departure"     ,src.getDeparture());
        obj.addProperty("departureTime" ,src.getDepartureDateTimeFormatted());
        obj.addProperty("signature"     ,src.getSignature());
        return obj;
    }

    @Override
    public Ticket deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
