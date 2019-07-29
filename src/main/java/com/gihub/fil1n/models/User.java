package com.gihub.fil1n.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_s")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Group ownedGroup;

//    @OneToOne
//    private UserPreferences userPreferences;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_habbits",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "habbit_id", referencedColumnName = "id")}
    )
    private List<Habbit> habbitList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "trusted_knot",
            joinColumns = {@JoinColumn(name = "trusted_user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "trusted_group_id", referencedColumnName = "id")}
    )
    private List<Group> whereIsTrusted;

    @ManyToMany
    @JoinTable(
            name = "untrusted_knot",
            joinColumns = {@JoinColumn(name = "untrusted_user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "untrusted_group_id", referencedColumnName = "id")}
    )
    private List<Group> whereIsUntrusted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "current_city_id"
    )
    private City currentCity;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "native_city_id"
    )
    private City nativeCity;

    @Column(name = "uni_id") // TODO: find uni db
    private Long uniId;

    @OneToMany(mappedBy = "user")
    private List<UserLanguage> userLanguageList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Group getOwnedGroup() {
        return ownedGroup;
    }

    public void setOwnedGroup(Group ownedGroup) {
        this.ownedGroup = ownedGroup;
    }

    public List<Habbit> getHabbitList() {
        return habbitList;
    }

    public void setHabbitList(List<Habbit> habbitList) {
        this.habbitList = habbitList;
    }

    public List<Group> getWhereIsTrusted() {
        return whereIsTrusted;
    }

    public void setWhereIsTrusted(List<Group> whereIsTrusted) {
        this.whereIsTrusted = whereIsTrusted;
    }

    public List<Group> getWhereIsUntrusted() {
        return whereIsUntrusted;
    }

    public void setWhereIsUntrusted(List<Group> whereIsUntrusted) {
        this.whereIsUntrusted = whereIsUntrusted;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public List<UserLanguage> getUserLanguageList() {
        return userLanguageList;
    }

    public void setUserLanguageList(List<UserLanguage> userLanguageList) {
        this.userLanguageList = userLanguageList;
    }

    public City getNativeCity() {
        return nativeCity;
    }

    public void setNativeCity(City nativeCity) {
        this.nativeCity = nativeCity;
    }

    public Long getUniId() {
        return uniId;
    }

    public void setUniId(Long uniId) {
        this.uniId = uniId;
    }

    public User() {}
}
