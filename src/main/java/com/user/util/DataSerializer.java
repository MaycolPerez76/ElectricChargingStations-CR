/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user.util;

/**
 *
 * @author mayco
 */

import com.google.gson.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Serializador personalizado para manejar LocalDate y LocalTime
 */
public class DataSerializer {
    
    private static Gson gson;
    
    static {
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public JsonElement serialize(LocalDate date, java.lang.reflect.Type type, 
                                                JsonSerializationContext context) {
                        return new JsonPrimitive(date.toString());
                    }
                })
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement json, java.lang.reflect.Type type, 
                                                JsonDeserializationContext context) {
                        return LocalDate.parse(json.getAsString());
                    }
                })
                .registerTypeAdapter(LocalTime.class, new JsonSerializer<LocalTime>() {
                    @Override
                    public JsonElement serialize(LocalTime time, java.lang.reflect.Type type, 
                                                JsonSerializationContext context) {
                        return new JsonPrimitive(time.toString());
                    }
                })
                .registerTypeAdapter(LocalTime.class, new JsonDeserializer<LocalTime>() {
                    @Override
                    public LocalTime deserialize(JsonElement json, java.lang.reflect.Type type, 
                                                JsonDeserializationContext context) {
                        return LocalTime.parse(json.getAsString());
                    }
                });
        
        gson = builder.create();
    }
    
    public static Gson getGson() {
        return gson;
    }
}
