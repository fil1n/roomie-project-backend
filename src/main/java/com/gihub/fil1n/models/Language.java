package com.gihub.fil1n.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table()
public class Language {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "language_name")
    private String name;

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
