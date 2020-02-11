package com.github.fil1n;

import com.github.fil1n.handlers.*;
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
                    javalinConfig.enableCorsForAllOrigins();
                });

        app.routes(() ->
                {
                    app.get("/group/:id", ctx -> GroupCRUD.getGroup(ctx.pathParam("id"), ctx));
                    app.post("/allgroups", ctx -> GroupCRUD.getRequiredGroups(ctx));
                    app.get("/profile/:id", ctx -> UserCRUD.getUserById(ctx.pathParam("id"), ctx));
                    app.get("/city/:id", ctx -> CityCRUD.getById(ctx.pathParam("id"), ctx));
                    app.get("/cities/:country", ctx -> CityCRUD.getByCountry(ctx.pathParam("country"), ctx));
                    app.get("/universities/:city_id", ctx -> UniversityCRUD.getListByCityId(ctx.pathParam("city_id"), ctx));
                    app.get("/specialities/:universityId", ctx -> UniversityCRUD.getFacultiesByUniversityId(ctx.pathParam("universityId"), ctx));
                    app.post("/register", ctx -> UserCRUD.addUser(ctx));
                    app.post("/creategroup", ctx -> GroupCRUD.addGroup(ctx));
                    app.get("/allcountries", ctx -> CountryCRUD.getAllCountries(ctx));
                    app.get("/member/:memberId/:groupId", ctx -> UserCRUD.memberInfo(ctx, ctx.pathParam("memberId"), ctx.pathParam("groupId")));
                    app.delete("/user/:id", ctx -> UserCRUD.deleteUserById(ctx.pathParam("id") , ctx));
                    app.delete("/group/:id", ctx -> GroupCRUD.deleteGroup(ctx.pathParam("id"), ctx));
                    app.patch("/user/:id", ctx -> UserCRUD.patchUser(ctx));
                    app.patch("/group/:id", ctx -> GroupCRUD.updateGroup(ctx));
                    app.get("/login", ctx -> AuthCRUD.isLoginValid(ctx));
                    app.get("/languages", ctx -> LanguageCRUD.getAll(ctx));
                }
        );

        app.start(9301);
    }

    //TODO: UserDesirializer, Test ALGO
}
