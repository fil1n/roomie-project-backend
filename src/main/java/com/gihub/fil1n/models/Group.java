package com.gihub.fil1n.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "knot")
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "knot")
    private List<User> users = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "knotList")
    private List<Question> questions = new ArrayList<>();

    private Long MAX_NUM_OF_USERS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getMAX_NUM_OF_USERS() {
        return MAX_NUM_OF_USERS;
    }

    public void setMAX_NUM_OF_USERS(Long MAX_NUM_OF_USERS) {
        this.MAX_NUM_OF_USERS = MAX_NUM_OF_USERS;
    }

    public Group() {}
}
