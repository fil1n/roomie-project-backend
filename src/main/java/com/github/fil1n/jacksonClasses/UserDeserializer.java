package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.fil1n.CryptoUtils;
import com.github.fil1n.dao.CityDao;
import com.github.fil1n.dao.LanguageDao;
import com.github.fil1n.dao.UniversityDao;
import com.github.fil1n.models.Language;
import com.github.fil1n.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDeserializer extends StdDeserializer<User> {
    private static CityDao cityDao = new CityDao();
    private static UniversityDao universityDao = new UniversityDao();
    private static LanguageDao languageDao = new LanguageDao();

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

        user.setName(node.get("userName").asText());
        user.setPassword(CryptoUtils.cryptString(node.get("password").asText()));
        user.setEmail(node.get("email").asText());
        user.setAdditionalInfo(node.get("userInfo").asText());
        user.setSex(User.Sex.valueOf(node.get("sex").asText()));

        if(node.get("birthCity") != null) {
            try {
                user.setNativeCity(cityDao.getById(node.get("birthCity").asLong()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        user.setPhone(node.get("phoneNumber").asText());

        if(node.get("speciality") != null) {
            try {
                user.setFaculty(universityDao.getFacultyById(node.get("speciality").asLong()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            user.setCurrentCity(cityDao.getById(node.get("currentCity").asLong()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        user.setRentalPeriod(node.get("rentalPeriod").asInt());
        user.setMaxRoommatesNumber(node.get("maxRoommatesNumber").asInt());
        user.setBirthDate(node.get("birthDate").asText());
        user.setAge(User.calculateAge(user.getBirthDate()));

        if(node.get("university") != null) {
            try {
                user.setUniversity(universityDao.getById(node.get("university").asLong()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Language> langs = new ArrayList<>();
        final Language[] language = {new Language()};

        ArrayNode newNode = (ArrayNode) node.withArray("languages");
        newNode.forEach(element -> {
            try {
                language[0] = languageDao.getById(element.get("id").asLong());
                langs.add(language[0]);
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        user.setUserLanguageList(langs);

        if(node.get("badHabits") != null) {
            String habbitList = node.get("badHabits").asText();
            user.setHabbitList(User.convertUserHabitsToArray(habbitList));
        }

        return user;
    }
}
