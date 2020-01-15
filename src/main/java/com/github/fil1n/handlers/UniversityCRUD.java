package com.github.fil1n.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fil1n.dao.UniversityDao;
import com.github.fil1n.jacksonClasses.JavalinJacksonUtils;
import com.github.fil1n.models.Faculty;
import com.github.fil1n.models.University;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UniversityCRUD {
    private static UniversityDao dao = new UniversityDao();


    public static void getListByCity(@NotNull String name, @NotNull Context ctx) {
        try {
            List<University> result = dao.getByCity(name);
            ObjectMapper mapper = JavalinJacksonUtils.getUniversityMapper();
            ctx.json(mapper.writeValueAsString(result));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getListByCityId(@NotNull String id, @NotNull Context ctx) {
        try {
            List<University> universities = dao.getByCityId(id);
            ObjectMapper mapper = JavalinJacksonUtils.getUniversityMapper();
            ctx.json(mapper.writeValueAsString(universities));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getFacultiesByUniversityId(@NotNull String  id, @NotNull Context ctx) {
        try {
            List<Faculty> faculties = dao.getFacultiesByUniversityId(id);
            ObjectMapper mapper = JavalinJacksonUtils.getFacultyMapper();
            ctx.json(mapper.writeValueAsString(faculties));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private UniversityCRUD() {}
}
