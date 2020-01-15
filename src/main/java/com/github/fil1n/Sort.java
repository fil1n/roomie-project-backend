package com.github.fil1n;

import com.github.fil1n.dao.GroupDao;
import com.github.fil1n.models.Group;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Sort {

    private static GroupDao dao = new GroupDao();

    private static ArrayList<Group> getFilterGroups(Group exampleGroup) throws Exception {
        try {

            ArrayList<Group> filteredGroupList = new ArrayList<>();
            ArrayList<Group> allGroups = (ArrayList) dao.getAllGroups();

            for (int i = 0; i < allGroups.size(); ++i) {
                if (allGroups.get(i).getCity().equals(exampleGroup.getCity())) {
                    filteredGroupList.add(allGroups.get(i));
                }
            }

            return filteredGroupList;

        }catch (Exception e) {
            e.printStackTrace();
        }

        throw  new Exception();
    }

    private static Pair<Double, Group> getGroupMatchingProportion(Group given , Group reference) {
        AtomicLong numOfUnique = new AtomicLong(0);
        AtomicLong numOfRequiredInTheGiven = new AtomicLong(0);

        Map<String, Boolean> isMet = new HashMap<>();
        Pair<Double, Group> result = null;

        ArrayList<String> requiredLangs = new ArrayList<>();
        ArrayList<String> requiredCountries = new ArrayList<>();

        for(int i = 0; i < reference.getTrustedUsers().size(); ++i) {
            reference.getTrustedUsers().get(i).getUserLanguageList().forEach(
                    userLanguage -> {
                        requiredLangs.add(userLanguage.getName());
                    }
            );

            requiredCountries.add(reference.getTrustedUsers().get(i).getNativeCity().getName());
        }

        ArrayList<String> givenLangs  =  new ArrayList<>();
        ArrayList<String> givenCountries = new ArrayList<>();

        for(int i = 0; i < given.getTrustedUsers().size(); ++i) {
            given.getTrustedUsers().get(i).getUserLanguageList().forEach(
                    userLanguage ->  {
                        givenLangs.add(userLanguage.getName());
                    }
            );

            givenCountries.add(given.getTrustedUsers().get(i).getNativeCity().getName());
        }



        for(int i = 0; i < givenLangs.size(); ++i) {
            String lang = givenLangs.get(i);

            if(!isMet.containsKey(lang)) {
                isMet.put(lang, Boolean.TRUE);
                numOfUnique.incrementAndGet();

                if(requiredLangs.contains(lang)) {
                    numOfRequiredInTheGiven.incrementAndGet();
                }
            }
        }

        isMet = new HashMap<>();

        AtomicLong numberOfUniqueCountries = new AtomicLong(0);
        AtomicLong numberOfRequiredCountriesInGiven = new AtomicLong(0);

        for (int i = 0; i < givenCountries.size(); ++i) {
            String country = givenCountries.get(i);

            if(!isMet.containsKey(country)) {
                isMet.put(country, Boolean.TRUE);
                numberOfUniqueCountries.incrementAndGet();

                if(requiredCountries.contains(country)) {
                    numberOfRequiredCountriesInGiven.incrementAndGet();
                }
            }
        }

        Double proportionOfRequiredLangsInGiven = Double.valueOf(String.valueOf(numOfRequiredInTheGiven)) / Double.valueOf(String.valueOf(numOfUnique));
        Double proportionOfRequiredCountriesInGiven = Double.valueOf(String.valueOf(numberOfRequiredCountriesInGiven)) / Double.valueOf(String.valueOf(numberOfUniqueCountries));
        Double finalProportion = (proportionOfRequiredCountriesInGiven + proportionOfRequiredLangsInGiven) / 2.0;

        result = new Pair<>(finalProportion, given);

        return result;
    }

    private static ArrayList<Group> getUserRecommendedGroup(ArrayList<Group> filtered, Group reference) {
        ArrayList<Pair<Double, Group>> unsortedList = new ArrayList<>();
        ArrayList<Group> result = new ArrayList<>();

        for(int i = 0; i < filtered.size(); ++i) {
            Group group = filtered.get(i);
            unsortedList.add(getGroupMatchingProportion(group, reference));
        }

        unsortedList.sort(getComp());

        for(int i = 0; i < unsortedList.size(); ++i) {
            result.add(unsortedList.get(i).component2());
        }

        return result;
    }


    public static ArrayList<Group> getRecommendedGroups(@NotNull Group example) throws Exception {
        try {
            return getUserRecommendedGroup((ArrayList<Group>) dao.getAllGroups(), example);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw  new Exception();
    }

    private static Comparator< Pair<Double, Group> > getComp() {
        Comparator comp = new Comparator<Pair<Double, Group>>() {

            @Override
            public int compare(Pair<Double, Group> a, Pair<Double, Group> b) {
                if(a.component1() > b.component1()) {
                    return 1;
                }

                if(a.component1() == b.component1()) {
                    return 0;
                }

                return -1;
            }
        };

        return  comp;
    }

    private Sort() {}
}
