package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gihub.fil1n.models.Faculty;

import java.io.IOException;

public class SpecialitySerializer extends StdSerializer<Faculty> {
    public SpecialitySerializer(Class<Faculty> t) {
        super(t);
    }

    public SpecialitySerializer() {this(null);}

    @Override
    public void serialize(Faculty faculty, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", faculty.getId());
            jsonGenerator.writeStringField("name", faculty.getName());
        jsonGenerator.writeEndObject();
    }
}
