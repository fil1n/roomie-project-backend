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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_preferences",
            joinColumns = {@JoinColumn(name = "preferences_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "habbit_id", referencedColumnName = "id")}
    )
    private List<Habbit> userPreferences;


    @OneToMany(mappedBy = "userPreferences")
    private List<LangPreferences> langPreferencesList;

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

    public List<LangPreferences> getLangPreferencesList() {
        return langPreferencesList;
    }

    public void setLangPreferencesList(List<LangPreferences> langPreferencesList) {
        this.langPreferencesList = langPreferencesList;
    }

    public List<Habbit> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(List<Habbit> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public UserPreferences() {}
}
