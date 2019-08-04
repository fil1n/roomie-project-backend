package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gihub.fil1n.CryptoUtils;
import com.gihub.fil1n.handlers.CityDao;
import com.gihub.fil1n.models.City;
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

        user.setFirstName(node.get("first_name").asText());
        user.setLastName(node.get("last_name").asText());
        user.setPassword(CryptoUtils.cryptString(node.get("password").asText()));
        user.setEmail(node.get("email").asText());
        user.setAditionalInfo(node.get("add_info").asText());
        user.setLang(node.get("lang").asText());
        CityDao dao = new CityDao();
        user.setCurrentCity(dao.getByName(node.get("curr_city").asText()));
        user.setNativeCity(dao.getByName(node.get("native_city").asText()));

        return user;
    }
}
