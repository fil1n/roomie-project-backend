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

    @Column(name = "current_city")
    private Long currentCityId;

    @Column(name = "native_city")
    private Long nativeCityId;

    @Column(name = "uni_id")
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

    public Long getCurrentCityId() {
        return currentCityId;
    }

    public void setCurrentCityId(Long currentCityId) {
        this.currentCityId = currentCityId;
    }

    public Long getNativeCityId() {
        return nativeCityId;
    }

    public void setNativeCityId(Long nativeCityId) {
        this.nativeCityId = nativeCityId;
    }

    public Long getUniId() {
        return uniId;
    }

    public void setUniId(Long uniId) {
        this.uniId = uniId;
    }

    public User() {}
}
