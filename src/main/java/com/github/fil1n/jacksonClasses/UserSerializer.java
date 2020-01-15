package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.fil1n.models.User;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {

    protected UserSerializer(Class<User> t) {
        super(t);
    }

    public UserSerializer() {
        this(null);
    }


    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", user.getName());
        jsonGenerator.writeEndObject();
    }
}
