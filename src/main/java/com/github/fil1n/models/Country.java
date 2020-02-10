package com.github.fil1n.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name_e")
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "country")
    private List<City> cityList;

    @OneToMany(mappedBy = "birthCountry")
    private Set<User> borned;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_pref_id")
    private UserPreferences countryPreferences;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public UserPreferences getCountryPreferences() {
        return countryPreferences;
    }

    public void setCountryPreferences(UserPreferences countryPreferences) {
        this.countryPreferences = countryPreferences;
    }

    public Set<User> getBorned() {
        return borned;
    }

    public void setBorned(Set<User> borned) {
        this.borned = borned;
    }

    public Country() {}
}
