package com.gihub.fil1n.handlers;

import com.gihub.fil1n.models.User;
import io.javalin.Context;

public class UserCrud {

    private static UserDao dao = new UserDao();

    public static void create(Context context) {
        try {
            System.out.print(context.body());
            User user = context.bodyAsClass(User.class);
            dao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void delete(Context context, String s) {
        try {
            Long id = Long.valueOf(s);
            dao.deleteById(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getAll(Context context) {

    }


    public static void getOne(Context context, String s) {
        try {
            Long id = Long.valueOf(s);
            User user = dao.queryForId(id);
            context.json(user);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void update(Context context, String s) {
        try {

            User user = context.bodyAsClass(User.class);
            user.setId(Long.valueOf(s));

            dao.update(user);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}
