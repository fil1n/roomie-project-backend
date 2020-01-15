package com.github.fil1n.models;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    enum VOTE_TYPE {POSITIVE, NEGATIVE};
    enum QUESTION_TYPE {ADD, DELETE};

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "body")
    private QUESTION_TYPE body;

//    @ManyToOne()
//    @JoinColumn(name = "person_id")
//    private User person;

    @OneToOne()
    @JoinColumn(name = "person_id")
    private User person;

    @ManyToOne ()
    @JoinColumn(
            name = "group_id"
    )
    private Group groupPolls;

    @Column(name = "num_of_positive_ans")
    private Integer numberOfAffirmativeAnswers;

    @Column(name = "num_of_negative_ans")
    private Integer numberOfNegativeAnswers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body.toString();
    }

    public void setBody(QUESTION_TYPE body) {
        this.body = body;
    }

    public Group getGroupPolls() {
        return groupPolls;
    }

    public void setGroupPolls(Group groupPolls) {
        this.groupPolls = groupPolls;
    }

//    public User getPerson() {
//        return person;
//    }
//
//    public void setPerson(User person) {
//        this.person = person;
//    }

    public Integer getNumberOfAffirmativeAnswers() {
        return numberOfAffirmativeAnswers;
    }

    public void setNumberOfAffirmativeAnswers(Integer numberOfAffirmativeAnswers) {
        this.numberOfAffirmativeAnswers = numberOfAffirmativeAnswers;
    }

    public Integer getNumberOfNegativeAnswers() {
        return numberOfNegativeAnswers;
    }

    public void setNumberOfNegativeAnswers(Integer numberOfNegativeAnswers) {
        this.numberOfNegativeAnswers = numberOfNegativeAnswers;
    }

    public void addVote(VOTE_TYPE type) {
        if(type == VOTE_TYPE.POSITIVE) {
            setNumberOfAffirmativeAnswers(this.numberOfAffirmativeAnswers + 1);
        }else{
            setNumberOfNegativeAnswers(this.numberOfNegativeAnswers + 1);
        }
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public Question() {}
}
