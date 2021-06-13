package com.github.fil1n.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "region_id")
    private Long regionId; // for future

    @Column(name = "latitude")
    private Double latitude; // for future

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    private List<University> universities;

    @OneToMany(mappedBy = "nativeCity")
    private List<User> nativeUserList;

    @OneToMany(mappedBy = "city")
    private List<Group> groupsCityList;

    @OneToMany(mappedBy = "currentCity")
    private List<User>  currentUserList;

    @ManyToOne()
    @JoinColumn(name = "citys_country_id")
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Group> getGroupsCityList() {
        return groupsCityList;
    }

    public void setGroupsCityList(List<Group> groupsCityList) {
        this.groupsCityList = groupsCityList;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<User> getNativeUserList() {
        return nativeUserList;
    }

    public void setNativeUserList(List<User> nativeUserList) {
        this.nativeUserList = nativeUserList;
    }

    public List<User> getCurrentUserList() {
        return currentUserList;
    }

    public void setCurrentUserList(List<User> currentUserList) {
        this.currentUserList = currentUserList;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof City)) {
            return false;
        }

        if(o == this) {
            return true;
        }

        return ((City) o).getName().equals(this.getName());
    }

    public City() {}
}
