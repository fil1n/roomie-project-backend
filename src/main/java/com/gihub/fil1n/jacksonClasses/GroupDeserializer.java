package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gihub.fil1n.models.Group;

import java.io.IOException;

public class GroupDeserializer extends StdDeserializer<Group> {
    protected GroupDeserializer(Class<?> vc) {
        super(vc);
    }

    protected GroupDeserializer() {
        this(null);
    }


    @Override
    public Group deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return null;
    }
}
