package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.fil1n.CryptoUtils;
import com.github.fil1n.dao.*;
import com.github.fil1n.models.Language;
import com.github.fil1n.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserPatchDeserializer extends StdDeserializer<User> {

    protected UserPatchDeserializer(Class<?> vc) {
        super(vc);
    }

    public UserPatchDeserializer() {
        this(null);
    }

    private static UniversityDao universityDao = new UniversityDao();
    private static CityDao cityDao = new CityDao();
    private static CountryDao countryDao = new CountryDao();
    private static LanguageDao languageDao = new LanguageDao();

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        JsonNode node =  jsonParser.getCodec().readTree(jsonParser);
        User user = new User();


        user.setId(node.get("id").asLong());
        user.setName(node.get("userName").asText());
        user.setPassword(CryptoUtils.cryptString(node.get("password").asText()));
        user.setEmail(node.get("email").asText());
        user.setSex(User.Sex.valueOf(node.get("sex").asText()));

        if(node.get("photo") != null) {
            String ph = node.get("photo").asText();
            user.setPhoto(Base64.getDecoder().decode(ph.getBytes("UTF-8")));
        }

        if(node.get("userInfo") != null) {
            user.setUserInfo(node.get("userInfo").asText());
        }

        if(node.get("birthCity") != null) {
            try {
                user.setNativeCity(cityDao.getById(node.get("birthCity").asLong()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(node.get("birthCountry") != null) {
            try {
                user.setBirthCountry(countryDao.getById(node.get("birthCountry").asLong()));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(node.get("phoneNumber") != null) {
            user.setPhone(node.get("phoneNumber").asText());
        }

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


        if(node.get("speciality") != null) {
            try {
                user.setFaculty(universityDao.getFacultyById(node.get("speciality").asLong()));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        ArrayNode newNode = (ArrayNode) node.withArray("languages");
        newNode.forEach(element -> {
            try {
                long id = element.get("id").asLong();
                Language lang = new Language();
                lang = languageDao.getById(id);
                langs.add(lang);
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
