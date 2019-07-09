package com.gihub.fil1n.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "habbit")
public class Habbit {

    public enum descriptionOptions {MUST_HAVE, MIGHT_HAVE, SHOULD_HAVE};

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "options")
    private descriptionOptions options;

    @ManyToMany(mappedBy = "habbitList")
    private List<User> userList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public descriptionOptions getOptions() {
        return options;
    }

    public void setOptions(descriptionOptions options) {
        this.options = options;
    }

    public Habbit() {}
}
