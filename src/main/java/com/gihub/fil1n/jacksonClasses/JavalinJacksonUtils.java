package com.gihub.fil1n.jacksonClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.gihub.fil1n.models.User;

public class JavalinJacksonUtils {
    private static SimpleModule module = new SimpleModule();
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        try {
            module.addSerializer(User.class, new UserSerializer());
            module.addDeserializer(User.class, new UserDesirializer());
            mapper.registerModule(module);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    private JavalinJacksonUtils() {}
}
