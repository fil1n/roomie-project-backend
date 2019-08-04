package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gihub.fil1n.models.User;

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
        jsonGenerator.writeStringField("first_name", user.getFirstName());
        jsonGenerator.writeStringField("last_name", user.getLastName());
        jsonGenerator.writeEndObject();
    }
}
