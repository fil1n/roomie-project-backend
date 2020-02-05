package com.github.fil1n.handlers;

import com.github.fil1n.Authentication;
import com.github.fil1n.dao.UserDao;
import com.github.fil1n.jacksonClasses.JavalinJacksonUtils;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class AuthCRUD {

    private static UserDao dao = new UserDao();

    public static void isLoginValid(@NotNull Context ctx) {
        try {
            String email = ctx.basicAuthCredentials().getUsername();
            String password = ctx.basicAuthCredentials().getPassword();

            if(Authentication.isPasswordCorrect(email, password)) {
                String id = String.valueOf(dao.getByLogin(email));
                ctx.json(JavalinJacksonUtils.getLoginMapper().writeValueAsString(dao.getByLogin(email)));
                return;
            }

            ctx.status(401);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private AuthCRUD() {}
}
