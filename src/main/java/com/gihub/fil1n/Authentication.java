import com.gihub.fil1n.dao.UserDao;
import com.gihub.fil1n.models.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Authentication {

    private static UserDao dao = new UserDao();

    public static boolean isRegistered(@NotNull String email, @NotNull String password) {
        try {
            List<User> list = dao.getByLogin(email);

            if(list.size() > 1) {
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isPasswordAcceptable(@NotNull String password) throws Exception {
        try {
            if (password.length() < 8) {
                return false;
            }

            if (password.length() > 128) {
                return false;
            }

            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }


    private Authentication() {}
}
