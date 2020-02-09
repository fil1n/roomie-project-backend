package com.github.fil1n.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fil1n.dao.CityDao;
import com.github.fil1n.jacksonClasses.JavalinJacksonUtils;
import com.github.fil1n.models.City;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CityCRUD {

    private static CityDao dao = new CityDao();

    public static void getById(@NotNull String id, @NotNull Context ctx) throws Exception {
        try {
            Long cityId = Long.valueOf(id);
            City city = dao.getById(cityId);

            ObjectMapper mapper = JavalinJacksonUtils.getCityMapper();
            String result = mapper.writeValueAsString(city);
            ctx.json(result);
        }catch (Exception e) {

        }
    }

    public static void getByCountry(@NotNull String name, @NotNull Context ctx) {
        try {
            List<City> cities = dao.getByCountry(name);
            String result = JavalinJacksonUtils.getCityMapper().writeValueAsString(cities);
            ctx.json(result);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private CityCRUD() {}
}
