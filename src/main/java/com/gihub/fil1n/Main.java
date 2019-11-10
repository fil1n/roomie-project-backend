package com.gihub.fil1n;

import com.gihub.fil1n.handlers.*;
import io.javalin.Javalin;


public class Main {
    public static void main(String[] args)  {


         // trash to init hibernate before making responses
        try {
            HibernateInit.getSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Javalin app = Javalin.create(javalinConfig ->
                {
                    javalinConfig.enableDevLogging();
                });

        app.routes(() ->
                {
                    app.get("/:id", ctx -> GroupCRUD.getGroup(ctx.pathParam("id"), ctx));
                    app.get("/all_groups", ctx -> GroupCRUD.getAllGroups(ctx));
                    app.get("/profile/:id", ctx -> UserCRUD.getUserById(ctx.pathParam("id"), ctx));
                    app.get("/city/:id", ctx -> CityCRUD.getById(ctx.pathParam("id"), ctx));
                    app.get("/cities/:country", ctx -> CityCRUD.getByCountry(ctx.pathParam("country"), ctx));
                    app.get("/universities/:city_id", ctx -> UniversityCRUD.getListByCityId(ctx.pathParam("city_id"), ctx));
                    app.get("/specialities/:universityId", ctx -> UniversityCRUD.getFacultiesByUniversityId(ctx.pathParam("id"), ctx));
                    app.post("/register", ctx -> UserCRUD.addUser(ctx));
                }
        );

        app.start(9200);
    }

    //TODO: /:cities/country
    // TODO: /universities/city_id
    // TODO: Dates in Group.class
}
