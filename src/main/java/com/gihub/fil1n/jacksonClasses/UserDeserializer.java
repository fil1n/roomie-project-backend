package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.gihub.fil1n.CryptoUtils;
import com.gihub.fil1n.dao.CityDao;
import com.gihub.fil1n.dao.UniversityDao;
import com.gihub.fil1n.models.Habbit;
import com.gihub.fil1n.models.Language;
import com.gihub.fil1n.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDeserializer extends StdDeserializer<User> {
    private static CityDao cityDao = new CityDao();
    private static UniversityDao universityDao = new UniversityDao();

    protected UserDeserializer(Class<?> vc) {
        super(vc);
    }

    public UserDeserializer() {
        this(null);
    }


    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node =  jsonParser.getCodec().readTree(jsonParser);
        User user = new User();

        user.setName(node.get("name").asText());
        user.setPassword(CryptoUtils.cryptString(node.get("password").asText()));
        user.setEmail(node.get("email").asText());
        user.setAdditionalInfo(node.get("userInfo").asText());
        user.setSex(User.Sex.valueOf(node.get("sex").asText()));
        user.setNativeCity(cityDao.getByName(node.get("birthCity").asText()));
        user.setPhone(node.get("phoneNumber").asText());
        user.setCurrentCity(cityDao.getByName(node.get("currentCity").asText()));
        user.setRentalPeriod(node.get("rentalPeriod").asInt());
        user.setMaxRoommatesNumber(node.get("maxRoommatesNumber").asInt());

        try {
            user.setFaculty(universityDao.getFacultyByName(node.get("speciality").asText()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        List<Language> langs = new ArrayList<>();
        Language language = new Language();

        ArrayNode newNode = (ArrayNode) node.withArray("languages");
        newNode.forEach(element -> {
            language.setId(element.get("id").asLong());
            language.setName(element.get("name").asText());
            langs.add(language);
        });

        user.setUserLanguageList(langs);

        List<Habbit> userHabits = new ArrayList<>();
        Habbit habbit = new Habbit();

        ArrayNode secondNode = (ArrayNode) node.withArray("badHabits");
        secondNode.forEach(element -> {
            habbit.setName(element.get("name").asText());
            habbit.setId(element.get("id").asLong());
            userHabits.add(habbit);
        });


        return user;
    }
}
