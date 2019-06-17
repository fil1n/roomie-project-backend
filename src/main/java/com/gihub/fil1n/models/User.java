package com.gihub.fil1n.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String fname;

    @Column(name = "last_name")
    private String lname;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_knot",
            joinColumns = {@JoinColumn(name = "knot_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    private List<Group> knot = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public List<Group> getKnot() {
        return knot;
    }

    public void setKnot(ArrayList<Group> knot) {
        this.knot = knot;
    }

    public User() {}
}
