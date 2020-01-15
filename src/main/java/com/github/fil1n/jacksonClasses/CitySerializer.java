package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.fil1n.models.City;

import java.io.IOException;

public class CitySerializer extends StdSerializer<City> {
    public CitySerializer(Class<City> t) {
        super(t);
    }

    public CitySerializer() {
        this(null);
    }


    @Override
    public void serialize(City city, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", city.getId());
        jsonGenerator.writeStringField("name", city.getName());
        jsonGenerator.writeEndObject();
    }
}
