package com.gihub.fil1n.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private List<Habbit> userPreferences;

    private List<String> userPreferedCountries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Habbit> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(List<Habbit> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public List<String> getUserPreferedCountries() {
        return userPreferedCountries;
    }

    public void setUserPreferedCountries(List<String> userPreferedCountries) {
        this.userPreferedCountries = userPreferedCountries;
    }

    public UserPreferences() {}
}
