package com.github.fil1n.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "language_name")
    private String name;

    @Column(name = "language_code")
    private String code;

    @ManyToMany(mappedBy = "userLanguageList")
    private List<User> users;

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

    public Language() {}
}
