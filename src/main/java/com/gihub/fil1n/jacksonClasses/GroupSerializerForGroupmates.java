package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gihub.fil1n.models.Group;
import com.gihub.fil1n.models.Question;

import java.io.IOException;
import java.util.Base64;

public class GroupSerializerForGroupmates  extends StdSerializer<Group> {
    protected GroupSerializerForGroupmates(Class<Group> t) {
        super(t);
    }

    protected GroupSerializerForGroupmates() {
        this(null);
    }

    @Override
    public void serialize(Group group, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", group.getId());
        jsonGenerator.writeStringField("name", group.getName());
        jsonGenerator.writeStringField("city", group.getCity().getName());
        jsonGenerator.writeStringField("groupInfo", group.getGroupInfo());
        jsonGenerator.writeNumberField("memberNumber", group.getTrustedUsers().size());
        jsonGenerator.writeNumberField("peopleNumber", group.getTrustedUsers().size() + group.getUntrustedFollowers().size());
        jsonGenerator.writeNumberField("rentalPeriod", group.getRentalPeriod());
        jsonGenerator.writeNumberField("free", group.getMAX_NUM_OF_USERS() - group.getTrustedUsers().size());

        jsonGenerator.writeArrayFieldStart("members");
        group.getTrustedUsers().forEach(
                member -> {
                    try {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeNumberField("id", member.getId());
                        jsonGenerator.writeStringField("name", member.getName());
                        jsonGenerator.writeNumberField("age", member.getAge());
                        jsonGenerator.writeStringField("userInfo", member.getUserInfo());
                        jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(member.getPhoto()));
                        jsonGenerator.writeEndObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        jsonGenerator.writeEndArray();

        jsonGenerator.writeArrayFieldStart("applications");
        group.getUntrustedFollowers().forEach(
                member -> {
                    try {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeNumberField("id", member.getId());
                        jsonGenerator.writeStringField("name", member.getName());
                        jsonGenerator.writeNumberField("age", member.getAge());
                        jsonGenerator.writeStringField("userInfo", member.getUserInfo());
                        jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(member.getPhoto()));
                        jsonGenerator.writeEndObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        jsonGenerator.writeEndArray();

        jsonGenerator.writeArrayFieldStart("polls");

            int size = group.getPolls().size();
            int counter = size - 5;
            if(counter < 0) {
                counter = 0;
            }


            for(int i = counter; i < size; ++i) {
                Question question = group.getPolls().get(i);
                jsonGenerator.writeNumberField("id", question.getId());
                jsonGenerator.writeStringField("type", question.getBody());
                jsonGenerator.writeStringField("person", question.getPerson().getName());
                jsonGenerator.writeNumberField("TRUE", question.getNumberOfAffirmativeAnswers());
                jsonGenerator.writeNumberField("FALSE", question.getNumberOfNegativeAnswers());
            }

        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
