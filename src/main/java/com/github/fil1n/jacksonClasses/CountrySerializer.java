package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.fil1n.models.Country;

import java.io.IOException;

public class CountrySerializer extends StdSerializer<Country> {
    protected CountrySerializer(Class<Country> t) {
        super(t);
    }

    public CountrySerializer() {
        this(null);
    }


    @Override
    public void serialize(Country country, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", country.getId());
        jsonGenerator.writeStringField("name", country.getName());
        jsonGenerator.writeEndObject();
    }
}
