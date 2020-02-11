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

public class ExampleGroupDeserializer extends StdDeserializer<Group> {

    protected ExampleGroupDeserializer(Class<?> vc) {
        super(vc);
    }

    public ExampleGroupDeserializer() {
        this(null);
    }


    private static UserDao userDao = new UserDao();
    private static CityDao cityDao = new CityDao();

    @Override
    public Group deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node  = jsonParser.getCodec().readTree(jsonParser);

        Group group = new Group();
        User user = new User();
        City city = new City();

        Long cityId = node.get("cityId").asLong();

        try {
            city = cityDao.getById(cityId);
        }catch (Exception e) {
            e.printStackTrace();
        }


        group.setMAX_NUM_OF_USERS(node.get("maxNumOfRoommates").asLong());
        group.setRentalPeriod(node.get("rentalPeriod").asInt());
        group.setCity(city);


        return group;
    }
}
