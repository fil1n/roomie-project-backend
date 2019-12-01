package com.gihub.fil1n.handlers;

import com.gihub.fil1n.Authentication;
import com.gihub.fil1n.CryptoUtils;
import com.gihub.fil1n.Validator;
import com.gihub.fil1n.dao.UserDao;
import com.gihub.fil1n.jacksonClasses.JavalinJacksonUtils;
import com.gihub.fil1n.models.User;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class UserCRUD {

    private static UserDao dao = new UserDao();
    
    public static void getUserById(@NotNull String id, @NotNull Context ctx) throws Exception {
        User user = null;

        try {

            if(ctx.basicAuthCredentials() == null) {
                ctx.status(403);
                return;
            }

            String email = ctx.basicAuthCredentials().getUsername();
            String password = ctx.basicAuthCredentials().getPassword();

            if(email == null || password == null || !Authentication.isPasswordCorrect(email, password)) {
                ctx.status(403);
                return;
            }

            if(CryptoUtils.isEqual(password, user.getPassword()) && user.getEmail().equals(email)) {
                String result = JavalinJacksonUtils.getUserOwnerMapper().writeValueAsString(user);
                ctx.json(result).status(200);
                return;
            }

            String result = JavalinJacksonUtils.getUserMapperForAuthenticatedUsers().writeValueAsString(user);
            ctx.json(result).status(200);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(@NotNull Context ctx) {
        try {

            User user = JavalinJacksonUtils.getUserDeserializerMapper().readValue(ctx.body(), User.class);

            if(!Validator.isEmailValid(user.getEmail())) {
                ctx.status(422);
                return;
            }

            dao.createUser(user);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserById(@NotNull String id, @NotNull Context ctx) {
        String email = ctx.basicAuthCredentials().getUsername();
        String password = ctx.basicAuthCredentials().getPassword();

        if(email == null || password == null || !Authentication.isPasswordCorrect(email, password)) {
            ctx.status(403);
            return;
        }

        try {
            User user = dao.getById(Long.valueOf(id));

            if(user == null) {
                ctx.json(404);
                return;
            }

            if(!email.equals(user.getEmail()) || !CryptoUtils.isEqual(password, user.getPassword())) {
                ctx.json(403);
                return;
            }

            Long longId = Long.valueOf(id);
            dao.deleteById(longId);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
