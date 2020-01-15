package com.github.fil1n.handlers;

import com.github.fil1n.Authentication;
import com.github.fil1n.dao.CountryDao;
import com.github.fil1n.jacksonClasses.JavalinJacksonUtils;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class CountryCRUD {
    private static CountryDao dao = new CountryDao();

    public static void getAllCountries(@NotNull Context ctx) {
        try {
            String email = ctx.basicAuthCredentials().getUsername();
            String password = ctx.basicAuthCredentials().getPassword();

            if(Authentication.isPasswordCorrect(email, password)) {
                ctx.status(403);
                return;
            }

            String result = JavalinJacksonUtils.getGroupMapperForAuthenticatedUsers().writeValueAsString(dao.getAllCountries());

            ctx.json(result);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
