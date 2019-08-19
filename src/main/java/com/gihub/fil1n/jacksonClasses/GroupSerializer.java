package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gihub.fil1n.models.Group;

import java.io.IOException;

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
        jsonGenerator.writeStartObject();
            group.getTrustedUsers().forEach(
                    member -> {
                        try {
                            jsonGenerator.writeNumberField("id", member.getId());
                            jsonGenerator.writeStringField("first_name", member.getFirstName());
                            jsonGenerator.writeStringField("last_name", member.getLastName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
            );
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
