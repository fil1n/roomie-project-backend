package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.gihub.fil1n.models.*;

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

    static {
        try {
            groupModuleForAuthenticatedUsers.addSerializer(Group.class, new GroupSerializerForAuthenticatedUsers());
            groupModuleForAuthenticatedUsers.addDeserializer(Group.class, new GroupDeserializer());
            groupMapperForAuthenticatedUsers.registerModule(groupModuleForAuthenticatedUsers);

            groupModuleForGroupmates.addSerializer(Group.class, new GroupSerializerForGroupmates());
            groupModuleForGroupmates.addDeserializer(Group.class, new GroupDeserializer());
            groupMapperForGroupmates.registerModule(groupModuleForGroupmates);

            userModuleForAuthenticatedUsers.addSerializer(User.class, new UserSerializerForAuthenticatedUsers());

            defaultUserModule.addSerializer(User.class, new UserSerializer());
            defaultUserModule.addDeserializer(User.class, new UserDeserializer());
            defaultUserMapper.registerModule(defaultUserModule);

            defaultGroupModule.addSerializer(Group.class, new GroupSerializer());
            defaultGroupMapper.registerModule(defaultGroupModule);

            cityModule.addSerializer(City.class, new CitySerializer());
            cityMapper.registerModule(cityModule);

            universityModule.addSerializer(University.class, new UniversitySerializer());
            universityMapper.registerModule(universityModule);

            facultyModule.addSerializer(Faculty.class, new SpecialitySerializer());
            facultyMapper.registerModule(facultyModule);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObjectMapper getUniversityMapper() {
        return universityMapper;
    }

    public static ObjectMapper getFacultyMapper() {
        return facultyMapper;
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

    private JavalinJacksonUtils() {}
}
