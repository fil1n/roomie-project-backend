package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.fil1n.models.Group;

import java.io.IOException;
import java.util.Base64;

public class GroupSerializer extends StdSerializer<Group> {
    protected GroupSerializer(Class<Group> t) {
        super(t);
    }

    protected GroupSerializer() {
        this(null);
    }

    @Override
    public void serialize(Group group, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", group.getName());
        jsonGenerator.writeStringField("city", group.getCity().getName());
        jsonGenerator.writeNumberField("num_of_trusted_members", group.getTrustedUsers().size());

        jsonGenerator.writeArrayFieldStart("trusted_members");
            group.getTrustedUsers().forEach(
                    member -> {
                        try {
                            jsonGenerator.writeStartObject();
                            jsonGenerator.writeNumberField("id", member.getId());
                            jsonGenerator.writeStringField("name", member.getName());
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
