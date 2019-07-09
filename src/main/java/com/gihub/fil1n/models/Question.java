package com.gihub.fil1n.models;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "ans")
    private Boolean ans;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getAns() {
        return ans;
    }

    public void setAns(Boolean ans) {
        this.ans = ans;
    }

    public Question() {}
}
