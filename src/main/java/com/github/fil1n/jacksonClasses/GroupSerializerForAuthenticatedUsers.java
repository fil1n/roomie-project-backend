package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.fil1n.models.Group;

import java.io.IOException;
import java.util.Base64;

public class GroupSerializerForAuthenticatedUsers extends StdSerializer<Group> {

    protected GroupSerializerForAuthenticatedUsers(Class<Group> t) {
        super(t);
    }

    protected GroupSerializerForAuthenticatedUsers() {
        this(null);
    }

    @Override
    public void serialize(Group group, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", group.getId());
            jsonGenerator.writeNumberField("ownerId", group.getOwner().getId());
            jsonGenerator.writeStringField("name", group.getName());
            jsonGenerator.writeStringField("city", group.getCity().getName());
            jsonGenerator.writeStringField("groupInfo", group.getGroupInfo());
            jsonGenerator.writeNumberField("memberNumber", group.getTrustedUsers().size() + 1);
            jsonGenerator.writeNumberField("peopleNumber", group.getTrustedUsers().size() + group.getUntrustedFollowers().size() + 1);
            jsonGenerator.writeNumberField("rentalPeriod", group.getRentalPeriod());
            jsonGenerator.writeNumberField("free", group.getMAX_NUM_OF_USERS() - group.getTrustedUsers().size() - 1);

            jsonGenerator.writeArrayFieldStart("members");

        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", group.getOwner().getId());
            jsonGenerator.writeStringField("name", group.getOwner().getName());
            jsonGenerator.writeNumberField("age", group.getOwner().getAge());
            jsonGenerator.writeStringField("userInfo", group.getOwner().getUserInfo());
            if(group.getOwner().getPhoto() != null) {
                jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(group.getOwner().getPhoto()));
            }
            jsonGenerator.writeEndObject();
                        } catch (IOException e) {
                    e.printStackTrace();
                }

                    group.getTrustedUsers().forEach(
                            member -> {
                                try {
                                    jsonGenerator.writeStartObject();
                                        jsonGenerator.writeNumberField("id", member.getId());
                                        jsonGenerator.writeStringField("name", member.getName());
                                        jsonGenerator.writeNumberField("age", member.getAge());
                                        jsonGenerator.writeStringField("userInfo", member.getUserInfo());
                                        if(member.getPhoto() != null) {
                                            jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(member.getPhoto()));
                                        }
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
                        if(member.getPhoto() != null) {
                            jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(member.getPhoto()));
                        }
                        jsonGenerator.writeEndObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
