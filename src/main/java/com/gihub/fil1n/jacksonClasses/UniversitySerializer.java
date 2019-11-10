package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gihub.fil1n.models.University;

import java.io.IOException;

public class UniversitySerializer extends StdSerializer<University> {

    public UniversitySerializer(Class<University> t) {
        super(t);
    }
    public UniversitySerializer() {this(null);}

    @Override
    public void serialize(University university, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", university.getId());
            jsonGenerator.writeStringField("name", university.getName());
        jsonGenerator.writeEndObject();
    }
}
