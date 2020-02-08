package com.github.fil1n.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "uni_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "university")
    private Set<User> students;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "university")
    private List<Faculty> facultiesList;

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

    public List<Faculty> getFacultiesList() {
        return facultiesList;
    }

    public void setFacultiesList(List<Faculty> facultiesList) {
        this.facultiesList = facultiesList;
    }


    public University() {

    }
}
