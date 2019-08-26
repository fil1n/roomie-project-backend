package com.gihub.fil1n;

import com.gihub.fil1n.handlers.GroupCRUD;
import com.gihub.fil1n.handlers.ImageCRUD;
import com.gihub.fil1n.handlers.UserCRUD;
import com.gihub.fil1n.jacksonClasses.JavalinJacksonUtils;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

public class Main {
    public static void main(String[] args)  {


         // trash to init hibernate before making responses
        try {
            HibernateInit.getSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }


        JavalinJackson.configure(JavalinJacksonUtils.getMapper());

        Javalin app = Javalin.create().port(9200).enableDebugLogging();

        app.routes(() ->
                {
                    app.get("/user/:id", ctx -> UserCRUD.getUserById(ctx.pathParam("id") , ctx));
                    app.post("/user", ctx -> UserCRUD.addUser(ctx));
                    app.get("/group/:id", ctx -> GroupCRUD.getGroup(ctx.pathParam("id"), ctx));
                    app.post("/group", ctx -> GroupCRUD.addGroup(ctx));
                    app.delete("/user/:id", ctx -> UserCRUD.deleteUserById(ctx.pathParam("id")));
                    app.get("/img/:id", ctx -> ImageCRUD.getImageById(ctx.pathParam("id"), ctx));
                    app.post("/image", ctx -> ImageCRUD.uploadImage(ctx));
                }
        );

        app.start();
    }

    //TODO: add DAO, algo for group search, deploy
}
