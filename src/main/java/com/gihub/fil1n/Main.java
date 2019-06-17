package com.gihub.fil1n;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.gihub.fil1n.handlers.UserCrud;
import com.gihub.fil1n.handlers.UserDao;
import com.gihub.fil1n.jacksonClasses.UserDesirializer;
import com.gihub.fil1n.models.User;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

public class Main {
    public static void main(String[] args) {
        User user = new User();

        user.setFname("123");
        user.setLname("312");

        try {
            new UserDao().create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Javalin app = Javalin.create().enableDebugLogging().port(9200);

//        SimpleModule module = new SimpleModule();
//
//        UserDesirializer desirializer = new UserDesirializer();
//
//        module.addDeserializer(User.class, desirializer);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        mapper.registerModule(module);
//
//        JavalinJackson.configure(mapper);

        app.routes( () -> {
                app.get("user/:id/", ctx -> UserCrud.getOne(ctx, ctx.pathParam("id")));
                app.post("user/", ctx -> UserCrud.create(ctx));
                app.delete("deleteuser/:id", ctx -> UserCrud.delete(ctx, ctx.pathParam("id")));
                app.patch("patchuser/:id", ctx -> UserCrud.update(ctx, ctx.pathParam("id")));
            }
        );

        app.start();
    }
}
