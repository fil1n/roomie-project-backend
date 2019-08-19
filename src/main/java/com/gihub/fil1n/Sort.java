package com.gihub.fil1n;

import com.gihub.fil1n.dao.GroupDao;
import com.gihub.fil1n.models.City;
import com.gihub.fil1n.models.Group;

import java.util.ArrayList;

public class Sort {

    private static GroupDao dao = new GroupDao();

    public static ArrayList<Group> getFilterGroups(Group exampleGroup) throws Exception {
        try {

            ArrayList<Group> filteredGroupList = new ArrayList<>();
            ArrayList<Group> allGroups = (ArrayList) dao.getAllGroups();

            for (int i = 0; i < allGroups.size(); ++i) {
                if (allGroups.get(i).getCity().equals(exampleGroup.getCity()) && allGroups.get(i).isDateOfLivingProper(exampleGroup.getSettlementDate(), exampleGroup.getEjectionDate())) {
                    filteredGroupList.add(allGroups.get(i));
                }
            }

            return filteredGroupList;

        }catch (Exception e) {
            e.printStackTrace();
        }

        throw  new Exception();
    }

    public static ArrayList<Group> makeSugarSort(Group exampleGroup, ArrayList<City> filtredList) {
        return null;
    }


    private Sort() {}
}
