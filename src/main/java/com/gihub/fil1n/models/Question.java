package com.gihub.fil1n.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "num_of_positive_ans")
    private Long numberOfPositiveAns;

    @Column(name = "num_of_negative_ans")
    private Long numberOfNegativeAns;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "question_node",
            joinColumns = {@JoinColumn(name = "knot_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")}
    )
    private List<Group> knotList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNumberOfPositiveAns() {
        return numberOfPositiveAns;
    }

    public void setNumberOfPositiveAns(Long numberOfPositiveAns) {
        this.numberOfPositiveAns = numberOfPositiveAns;
    }

    public Long getNumberOfNegativeAns() {
        return numberOfNegativeAns;
    }

    public void setNumberOfNegativeAns(Long numberOfNegativeAns) {
        this.numberOfNegativeAns = numberOfNegativeAns;
    }

    public Question() {}
}
