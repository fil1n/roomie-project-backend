package com.gihub.fil1n.handlers;

import com.gihub.fil1n.dao.GroupDao;
import com.gihub.fil1n.models.Group;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GroupCRUD {

    private static GroupDao dao = new GroupDao();

    public static void getGroup(@NotNull String id, @NotNull Context ctx) {
        try {
            Long groupId = Long.valueOf(id);
            Group group = dao.getGroup(id);
            ctx.json(group).status(200);
            return;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addGroup(@NotNull Context context) {
        try {
            Group group = context.bodyAsClass(Group.class);
            dao.addGroup(group);
            context.status(200);
        }catch (Exception e) {
            e.printStackTrace();
            context.status(404);
        }
    }

    public static void deleteGroup(@NotNull String id, @NotNull Context ctx) {
        try {
            dao.deleteGroup(id);
            ctx.status(204);
        }catch (Exception e) {
            e.printStackTrace();
            ctx.status(404);
        }
    }

    public static void updateGroup(@NotNull Group group, @NotNull Context ctx) {
        try {
            dao.updateGroup(group);
            ctx.status(200);
            return;
        }catch (Exception e) {
            e.printStackTrace();
            ctx.status();
        }
    }

    public static void getRequiredGroups(@NotNull Context ctx) throws Exception {

    }
}
