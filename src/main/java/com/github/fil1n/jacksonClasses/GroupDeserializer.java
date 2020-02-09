package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.fil1n.dao.CityDao;
import com.github.fil1n.dao.UserDao;
import com.github.fil1n.models.City;
import com.github.fil1n.models.Group;
import com.github.fil1n.models.User;

import java.io.IOException;

public class GroupDeserializer extends StdDeserializer<Group> {
    private UserDao userDao = new UserDao();
    private CityDao cityDao = new CityDao();

    protected GroupDeserializer(Class<?> vc) {
        super(vc);
    }

    protected GroupDeserializer() {
        this(null);
    }


    @Override
    public Group deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node  = jsonParser.getCodec().readTree(jsonParser);

        Group group = new Group();
        User user = new User();
        City city = new City();

        Long userId = node.get("ownerId").asLong();
        Long cityId = node.get("cityId").asLong();

        try {
            city = cityDao.getById(cityId);
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            user = userDao.getById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        group.setMAX_NUM_OF_USERS(node.get("maxNum").asLong());
        group.setName(node.get("name").asText());
        group.setOwner(user);
        group.setRentalPeriod(node.get("rentalPeriod").asInt());
        group.setCity(city);
        return group;
    }
}
