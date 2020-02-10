package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.fil1n.models.*;

public class JavalinJacksonUtils {

    private static SimpleModule defaultUserModule = new SimpleModule();
    private static ObjectMapper defaultUserMapper = new ObjectMapper();
    private static SimpleModule groupModuleForAuthenticatedUsers = new SimpleModule();
    private static ObjectMapper groupMapperForAuthenticatedUsers = new ObjectMapper();
    private static SimpleModule groupModuleForGroupmates = new SimpleModule();
    private static ObjectMapper groupMapperForGroupmates = new ObjectMapper();
    private static SimpleModule userModuleForAuthenticatedUsers = new SimpleModule();
    private static ObjectMapper userMapperForAuthenticatedUsers = new ObjectMapper();
    private static SimpleModule defaultGroupModule = new SimpleModule();
    private static ObjectMapper defaultGroupMapper = new ObjectMapper();
    private static SimpleModule cityModule = new SimpleModule();
    private static ObjectMapper cityMapper = new ObjectMapper();
    private static SimpleModule universityModule = new SimpleModule();
    private static ObjectMapper universityMapper = new ObjectMapper();
    private static SimpleModule facultyModule = new SimpleModule();
    private static ObjectMapper facultyMapper = new ObjectMapper();
    private static SimpleModule userOwnerModule = new SimpleModule();
    private static ObjectMapper userOwnerMapper = new ObjectMapper();
    private static SimpleModule userDeserializerModule = new SimpleModule();
    private static ObjectMapper userDeserializerMapper = new ObjectMapper();
    private static SimpleModule countryModule = new SimpleModule();
    private static ObjectMapper countryMapper = new ObjectMapper();
    private static ObjectMapper languageMapper = new ObjectMapper();
    private static SimpleModule languageModule = new SimpleModule();
    private static SimpleModule loginModule = new SimpleModule();
    private static ObjectMapper loginMapper = new ObjectMapper();
    private static SimpleModule userPatchModule = new SimpleModule();
    private static ObjectMapper userPatchMapper = new ObjectMapper();

    static {
        try {
            loginModule.addSerializer(User.class, new LoginSerializer());
            loginMapper.registerModule(loginModule);

            languageModule.addSerializer(Language.class, new LanguageSerializer());
            languageMapper.registerModule(languageModule);

            groupModuleForAuthenticatedUsers.addSerializer(Group.class, new GroupSerializerForAuthenticatedUsers());
            groupModuleForAuthenticatedUsers.addDeserializer(Group.class, new GroupDeserializer());
            groupMapperForAuthenticatedUsers.registerModule(groupModuleForAuthenticatedUsers);

            groupModuleForGroupmates.addSerializer(Group.class, new GroupSerializerForGroupmates());
            groupModuleForGroupmates.addDeserializer(Group.class, new GroupDeserializer());
            groupMapperForGroupmates.registerModule(groupModuleForGroupmates);

            userModuleForAuthenticatedUsers.addSerializer(User.class, new UserSerializerForAuthenticatedUsers());
            userMapperForAuthenticatedUsers.registerModule(userModuleForAuthenticatedUsers);

            defaultUserModule.addSerializer(User.class, new UserSerializer());
            defaultUserModule.addDeserializer(User.class, new UserDeserializer());
            defaultUserMapper.registerModule(defaultUserModule);

            defaultGroupModule.addSerializer(Group.class, new GroupSerializer());
            defaultGroupModule.addDeserializer(Group.class, new GroupDeserializer());
            defaultGroupMapper.registerModule(defaultGroupModule);

            cityModule.addSerializer(City.class, new CitySerializer());
            cityMapper.registerModule(cityModule);

            universityModule.addSerializer(University.class, new UniversitySerializer());
            universityMapper.registerModule(universityModule);

            facultyModule.addSerializer(Faculty.class, new SpecialitySerializer());
            facultyMapper.registerModule(facultyModule);

            userOwnerModule.addSerializer(User.class, new UserSerializerForOwner());
            userOwnerMapper.registerModule(userOwnerModule);

            userDeserializerModule.addDeserializer(User.class, new UserDeserializer());
            userDeserializerMapper.registerModule(userDeserializerModule);

            countryModule.addSerializer(Country.class, new CountrySerializer());
            countryMapper.registerModule(countryModule);

            userPatchModule.addDeserializer(User.class, new UserPatchDeserializer());
            userPatchMapper.registerModule(userPatchModule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObjectMapper getUniversityMapper() {
        return universityMapper;
    }

    public static ObjectMapper getLanguageMapper() {
        return languageMapper;
    }

    public static ObjectMapper getLoginMapper() {
        return loginMapper;
    }

    public static ObjectMapper getFacultyMapper() {
        return facultyMapper;
    }

    public static ObjectMapper getUserMapperForAuthenticatedUsers() {
        return userMapperForAuthenticatedUsers;
    }

    public static ObjectMapper getUserOwnerMapper() {
        return userOwnerMapper;
    }

    public static ObjectMapper getUserPatchMapper() { return userPatchMapper; }

    public static ObjectMapper getUserDeserializerMapper() {
        return userDeserializerMapper;
    }

    public static ObjectMapper getDefaultUserMapper() {
        return defaultUserMapper;
    }

    public static ObjectMapper getGroupMapperForGroupmates() {
        return groupMapperForGroupmates;
    }

    public static ObjectMapper getGroupMapperForAuthenticatedUsers() {
        return groupMapperForAuthenticatedUsers;
    }

    public static ObjectMapper getDefaultGroupMapper() {
        return defaultGroupMapper;
    }

    public static ObjectMapper getCityMapper() {
        return cityMapper;
    }

    public static ObjectMapper getCountryMapper() {
        return countryMapper;
    }

    private JavalinJacksonUtils() {}
}
