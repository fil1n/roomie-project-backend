package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gihub.fil1n.dao.CityDao;
import com.gihub.fil1n.dao.UserDao;
import com.gihub.fil1n.models.City;
import com.gihub.fil1n.models.Group;
import com.gihub.fil1n.models.User;

import java.io.IOException;
import java.time.LocalDate;

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

        Long userId = node.get("owner_id").asLong();
        String cityName = node.get("city_name").asText();
        LocalDate sDate  = LocalDate.parse(node.get("s_date").asText());
        LocalDate eDate = LocalDate.parse(node.get("e_date").asText());

        try {
            city = cityDao.getByName(cityName);
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            user = userDao.getById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        group.setName(node.get("name").asText());
        group.setOwner(user);
        group.setCity(city);
        group.setSettlementDate(sDate);
        group.setEjectionDate(eDate);

        return group;
    }
}
