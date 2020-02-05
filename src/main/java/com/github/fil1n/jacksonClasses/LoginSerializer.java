package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.fil1n.models.User;

import java.io.IOException;
import java.util.Base64;

public class LoginSerializer extends StdSerializer<User> {


    protected LoginSerializer(Class<User> t) {
        super(t);
    }

    public LoginSerializer() {
        this(null);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", user.getId());
        jsonGenerator.writeStringField("name", user.getName());
        if(user.getPhoto() != null) {
            jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(user.getPhoto()));
        }
        jsonGenerator.writeEndObject();
    }
}
