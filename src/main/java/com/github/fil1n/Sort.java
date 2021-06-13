package com.github.fil1n;

import com.github.fil1n.dao.GroupDao;
import com.github.fil1n.models.Group;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Sort {

    private static final GroupDao groupDao = new GroupDao();

    public static List<Group> getGroups(@NotNull Group example) throws Exception {
        List<Group> result = new ArrayList<>();

        List<Group> allGroups = groupDao.getAllGroups();

        for (Group t : allGroups) {
            if (!t.getCity().getId().equals(example.getCity().getId())) {
                continue;
            }

            if (t.getRentalPeriod() < example.getRentalPeriod()) {
                continue;
            }

            if ((t.getMAX_NUM_OF_USERS() - t.getTrustedUsers().size()) == 0) {
                continue;
            }

            if (t.getMAX_NUM_OF_USERS() > example.getMAX_NUM_OF_USERS()) {
                continue;
            }

            result.add(t);
        }

        return result;
    }


    private Sort() {}
}
