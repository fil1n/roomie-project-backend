package com.github.fil1n.handlers;

import com.github.fil1n.Authentication;
import com.github.fil1n.Sort;
import com.github.fil1n.dao.GroupDao;
import com.github.fil1n.dao.UserDao;
import com.github.fil1n.jacksonClasses.JavalinJacksonUtils;
import com.github.fil1n.models.Group;
import com.github.fil1n.models.User;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GroupCRUD {

    private static GroupDao dao = new GroupDao();
    private static UserDao userDao = new UserDao();

    public static void getGroup(@NotNull String id, @NotNull Context ctx) {
        try {

            String email = ctx.basicAuthCredentials().getUsername();
            String password = ctx.basicAuthCredentials().getPassword();

            Group group = dao.getGroupById(id);

            if(!Authentication.isPasswordCorrect(email, password)) {
                String result = JavalinJacksonUtils.getDefaultGroupMapper().writeValueAsString(group);
                ctx.json(result);
                return;
            }

            try {
                User user = userDao.getByLogin(email).get(0);

                System.out.println(user.getId());

                if(user.getId() == group.getOwner().getId()) {
                    ctx.json(JavalinJacksonUtils.getGroupMapperForGroupmates().writeValueAsString(group));
                    return;
                }

                if (group.getTrustedUsers() != null && group.getTrustedUsers().contains(user)) {
                    String result = JavalinJacksonUtils.getGroupMapperForGroupmates().writeValueAsString(group);
                    ctx.json(result);
                    return;
                }

                String result = JavalinJacksonUtils.getGroupMapperForAuthenticatedUsers().writeValueAsString(group);
                ctx.json(result);
                return;
            }catch (Exception e) {
                e.printStackTrace();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addGroup(@NotNull Context context) {
        try {
            String email = context.basicAuthCredentials().getUsername();
            String password = context.basicAuthCredentials().getPassword();

            if(!Authentication.isPasswordCorrect(email, password)) {
                context.status(401);
                return;
            }

            User user = userDao.getByLogin(email).get(0);

            if(user.getWhereIsTrusted().size() > 0) {
                context.status(403);
                return;
            }

            Group group = JavalinJacksonUtils.getDefaultGroupMapper().readValue(context.body(), Group.class);
            dao.addGroup(group);
            context.status(200);
        }catch (Exception e) {
            e.printStackTrace();
            context.status(404);
        }
    }

    public static void deleteGroup(@NotNull String id, @NotNull Context ctx) {
        try {
            String email = ctx.basicAuthCredentials().getUsername();
            String password = ctx.basicAuthCredentials().getPassword();

            if(!Authentication.isPasswordCorrect(email, password)) {
                ctx.status(401);
                return;
            }

            User user = userDao.getByLogin(email).get(0);
            Group group = dao.getGroupById(id);

            if(!user.getOwnedGroup().equals(group)) {
                ctx.status(403);
                return;
            }

            dao.deleteGroup(id);
            ctx.status(204);
        }catch (Exception e) {
            e.printStackTrace();
            ctx.status(404);
        }
    }

    public static void updateGroup(@NotNull Context ctx) {
        try {

            String email = ctx.basicAuthCredentials().getUsername();
            String password = ctx.basicAuthCredentials().getPassword();

            if(!Authentication.isPasswordCorrect(email, password)) {
                ctx.status(403);
                return;
            }

            User user = userDao.getByLogin(email).get(0);
            Group group = ctx.bodyAsClass(Group.class);

            if(!user.getOwnedGroup().equals(group)) {
                ctx.status(403);
                return;
            }

            dao.updateGroup(group);
            ctx.status(200);
        }catch (Exception e) {
            e.printStackTrace();
            ctx.status();
        }
    }

    public static void getAllGroups(@NotNull Context ctx) {
        try {
            ctx.json(dao.getAllGroups());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getRequiredGroups(@NotNull Context ctx) throws Exception {
        try {
            String email = ctx.basicAuthCredentials().getUsername();
            String password = ctx.basicAuthCredentials().getPassword();

            if(!Authentication.isPasswordCorrect(email, password)) {
                ctx.status(401);
                return;
            }

            Group group = ctx.bodyAsClass(Group.class);

            ArrayList<Group> groups = Sort.getRecommendedGroups(group);

            ctx.json(group);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
