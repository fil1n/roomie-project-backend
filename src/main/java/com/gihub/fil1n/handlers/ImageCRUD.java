package com.gihub.fil1n.handlers;

import com.gihub.fil1n.dao.ImageDao;
import com.gihub.fil1n.models.Image;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

public class ImageCRUD {

    private static ImageDao dao = new ImageDao();

    public static void uploadImage(@NotNull Context ctx) {
        try {
            Image image = ctx.bodyAsClass(Image.class);
            dao.saveImage(image);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getImageById(@NotNull String id, @NotNull Context ctx) {
        try {
            Long imageId = Long.valueOf(id);
            Image image = dao.getImageById(imageId);
            ctx.json(image);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ImageCRUD() {}
}
