package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.fil1n.models.User;

import java.io.IOException;
import java.util.Base64;

public class UserSerializerForAuthenticatedUsers extends StdSerializer<User> {

    protected UserSerializerForAuthenticatedUsers(Class<User> t) {
        super(t);
    }

    public UserSerializerForAuthenticatedUsers() {
        this(null);
    }


    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
         jsonGenerator.writeStartObject();

         jsonGenerator.writeNumberField("id", user.getId());
         jsonGenerator.writeStringField("name", user.getName());
         jsonGenerator.writeNumberField("age", user.getAge());
         jsonGenerator.writeStringField("city", user.getNativeCity().getName());
         jsonGenerator.writeStringField("userInfo", user.getUserInfo());
         jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(user.getPhoto()));
         jsonGenerator.writeStringField("birthDate", user.getBirthDate());
         jsonGenerator.writeStringField("university", user.getFaculty().getUniversity().getName());
         jsonGenerator.writeStringField("speciality", user.getFaculty().getName());

         jsonGenerator.writeArrayFieldStart("languages");
            for(int i = 0; i < user.getUserLanguageList().size(); ++i) {
                jsonGenerator.writeString(user.getUserLanguageList().get(i).getName());
            }
         jsonGenerator.writeEndArray();

         jsonGenerator.writeArrayFieldStart("badHabits");
            for(int i = 0; i < user.getHabbitList().size(); ++i) {
                jsonGenerator.writeString(user.getHabbitList().get(i).getName());
            }
         jsonGenerator.writeEndArray();

         jsonGenerator.writeStringField("phoneNumber", user.getPhone());

         jsonGenerator.writeEndObject();
    }
}
