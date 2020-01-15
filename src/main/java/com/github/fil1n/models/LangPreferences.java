package com.github.fil1n.models;

import javax.persistence.*;

@Entity
@Table(name = "lang_preferences")
public class LangPreferences {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "lang")
    private String lang;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pref_id"
    )
    private UserPreferences userPreferences;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public UserPreferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    public LangPreferences() {
    }
}
