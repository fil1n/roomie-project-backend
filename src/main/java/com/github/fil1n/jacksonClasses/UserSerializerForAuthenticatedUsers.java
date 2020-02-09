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
         user.setAge(User.calculateAge(user.getBirthDate()));
         jsonGenerator.writeStartObject();

         jsonGenerator.writeNumberField("id", user.getId());
         jsonGenerator.writeStringField("name", user.getName());
         jsonGenerator.writeNumberField("age", user.getAge());

         if(user.getNativeCity() != null) {
             jsonGenerator.writeStringField("city", user.getNativeCity().getName());
         }

         if(user.getUserInfo() != null) {
             jsonGenerator.writeStringField("userInfo", user.getUserInfo());
         }

         if(user.getPhoto() != null) {
             jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(user.getPhoto()));
         }

         jsonGenerator.writeStringField("birthDate", user.getBirthDate());

         if(user.getUniversity() != null) {
             jsonGenerator.writeNumberField("universityId", user.getUniversity().getId());
             jsonGenerator.writeStringField("universityName", user.getUniversity().getName());
         }

         if(user.getFaculty() != null) {
             jsonGenerator.writeStringField("speciality", user.getFaculty().getName());
         }

         jsonGenerator.writeArrayFieldStart("languages");
            for(int i = 0; i < user.getUserLanguageList().size(); ++i) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("languageId", user.getUserLanguageList().get(i).getId());
                jsonGenerator.writeStringField("languageName", user.getUserLanguageList().get(i).getName());
                jsonGenerator.writeEndObject();
            }
         jsonGenerator.writeEndArray();

         if(user.getHabbitList() != null) {
                jsonGenerator.writeArrayFieldStart("badHabits");
                for (int i = 0; i < user.getHabbitList().size(); ++i) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("name", user.getHabbitList().get(i).getName());
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();
         }

         jsonGenerator.writeStringField("phoneNumber", user.getPhone());

         jsonGenerator.writeEndObject();
    }
}
