package com.github.fil1n.handlers;

import com.github.fil1n.Authentication;
import com.github.fil1n.CryptoUtils;
import com.github.fil1n.Validator;
import com.github.fil1n.dao.GroupDao;
import com.github.fil1n.dao.UserDao;
import com.github.fil1n.jacksonClasses.JavalinJacksonUtils;
import com.github.fil1n.models.Group;
import com.github.fil1n.models.User;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class UserCRUD {

    private static UserDao dao = new UserDao();
    private static GroupDao groupDao = new GroupDao();

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

            if(dao.getByLogin(user.getEmail()).size() > 0) {
                ctx.status(409);
                return;
            }

            if(!Validator.isEmailValid(user.getEmail())) {
                ctx.status(422);
                return;
            }

            dao.createUser(user);
            ctx.json(JavalinJacksonUtils.getLoginMapper().writeValueAsString(dao.getByLogin(user.getEmail())));
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

    public static void memberInfo(@NotNull Context ctx, @NotNull String memberId, @NotNull String groupId) {
        try {
            Group group = groupDao.getGroupById(groupId);
            for(int i = 0; i < group.getTrustedUsers().size(); ++i) {
                if(group.getTrustedUsers().get(i).getId() == Long.valueOf(memberId)) {
                    ctx.json("{" + '\"' + "isMember" + '\"' + ":" + '\"' + "TRUE" + '\"' +  "}");
                    return;
                }
            }

            ctx.json("{" + '\"' + "isMember" + '\"' + ":" + '\"' + "FALSE" + '\"' +  "}");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void patchUser(@NotNull Context ctx) {
        String email = null;
        String password = null;

        try {
            email = ctx.basicAuthCredentials().getUsername();
            password = ctx.basicAuthCredentials().getPassword();

            if(!Authentication.isPasswordCorrect(email, password)) {
                ctx.status(403);
                return;
            }

            User user = ctx.bodyAsClass(User.class);

            if(!user.getEmail().equals(email) || !CryptoUtils.isEqual(password, user.getPassword())) {
                ctx.status(403);
                return;
            }

            dao.update(user);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
