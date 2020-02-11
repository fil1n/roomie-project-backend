package com.github.fil1n;

import com.github.fil1n.dao.GroupDao;
import com.github.fil1n.models.Group;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewSort {

    private static GroupDao groupDao = new GroupDao();

    public static List<Group> getGroups(@NotNull Group example) {
        List<Group> result = new ArrayList<>();

        List<Group> all = null;
        try {
            all = groupDao.getAllGroups();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0 ; i < all.size(); ++i) {
            Group t = all.get(i);

            if(!t.getCity().getId().equals(example.getCity().getId())) {
                continue;
            }

            if(t.getRentalPeriod() < example.getRentalPeriod()) {
                continue;
            }

            if((t.getMAX_NUM_OF_USERS() - t.getTrustedUsers().size()) == 0) {
                continue;
            }

            if(t.getMAX_NUM_OF_USERS() > example.getMAX_NUM_OF_USERS()) {
                continue;
            }

            result.add(t);
        }

        return result;
    }


    private NewSort() {}
}
