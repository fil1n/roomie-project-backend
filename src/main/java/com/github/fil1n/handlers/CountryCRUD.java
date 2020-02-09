package com.github.fil1n.handlers;

import com.github.fil1n.Authentication;
import com.github.fil1n.dao.CountryDao;
import com.github.fil1n.jacksonClasses.JavalinJacksonUtils;
import com.github.fil1n.models.Country;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CountryCRUD {
    private static CountryDao dao = new CountryDao();

    public static void getAllCountries(@NotNull Context ctx) {
        try {

            String result = JavalinJacksonUtils.getCountryMapper().writeValueAsString(dao.getAllCountries());

            ctx.json(result);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
