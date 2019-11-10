package com.gihub.fil1n;

import com.gihub.fil1n.dao.UserDao;
import com.gihub.fil1n.models.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Authentication {
    private static UserDao dao = new UserDao();

    public static boolean isPasswordCorrect(@NotNull String email, @NotNull String password) {

        try {
            List<User> list = dao.getByLogin(email);

            if(list.size() > 1) {
                System.out.println("ERROR: one login belongs to multiple users!\n");
                throw new Exception();
            }

            if(list.size() == 0) {
                return false;
            }

            User user = list.get(0);

            return CryptoUtils.isEqual(password, user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private Authentication() {}
}
