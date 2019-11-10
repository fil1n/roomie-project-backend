package com.gihub.fil1n.models;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city")
    private List<University> universities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nativeCity")
    private List<User> nativeUserList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private List<Group> groupsCityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currentCity")
    private List<User>  currentUserList;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
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

        if(((City) o).getName() == this.getName()) {
            return true;
        }

        return false;
    }

    public City() {}
}
