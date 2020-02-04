package com.github.fil1n.handlers;

import com.github.fil1n.dao.LanguageDao;
import com.github.fil1n.jacksonClasses.JavalinJacksonUtils;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJackson;
import org.jetbrains.annotations.NotNull;

public class LanguageCRUD {

    private  static LanguageDao dao = new LanguageDao();

    public static void getAll(@NotNull Context ctx) throws Exception {
        try {
            String res = JavalinJacksonUtils.getLanguageMapper().writeValueAsString(dao.getAll());
            ctx.json(res);
            return;
        }catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

    private LanguageCRUD() {}
}
