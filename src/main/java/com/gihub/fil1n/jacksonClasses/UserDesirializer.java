package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gihub.fil1n.models.User;

import java.io.IOException;

public class UserDesirializer extends StdDeserializer<User> {
    protected UserDesirializer(Class<?> vc) {
        super(vc);
    }

    public UserDesirializer() {
        this(null);
    }


    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node =  jsonParser.getCodec().readTree(jsonParser);
        User user = new User();

        user.setFname(node.get("first_name").asText());
        user.setLname(node.get("last_name").asText());

        return user;
    }
}
