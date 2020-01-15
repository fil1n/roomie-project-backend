package com.github.fil1n.handlers;

import com.github.fil1n.Authentication;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class AuthCRUD {

    public static void isLoginValid(@NotNull Context ctx) {
        try {
            String email = ctx.basicAuthCredentials().getUsername();
            String password = ctx.basicAuthCredentials().getPassword();

            if(Authentication.isPasswordCorrect(email, password)) {
                ctx.json("{ans : \"true\" }");
                return;
            }

            ctx.json("{ans : \"false\"}");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private AuthCRUD() {}
}
