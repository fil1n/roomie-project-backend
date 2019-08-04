package com.gihub.fil1n.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group_s")
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @Column(name = "max_num_of_users")
    private Long MAX_NUM_OF_USERS;

    @ManyToMany(mappedBy = "whereIsTrusted")
    private List<User> trustedUsers;

    @ManyToMany(mappedBy = "whereIsUntrusted")
    private List<User> untrustedFollowers;

    @OneToMany(mappedBy = "knot")
    private List<CommunicationType> communicationTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getMAX_NUM_OF_USERS() {
        return MAX_NUM_OF_USERS;
    }

    public void setMAX_NUM_OF_USERS(Long MAX_NUM_OF_USERS) {
        this.MAX_NUM_OF_USERS = MAX_NUM_OF_USERS;
    }

    public List<User> getTrustedUsers() {
        return trustedUsers;
    }

    public void setTrustedUsers(List<User> trustedUsers) {
        this.trustedUsers = trustedUsers;
    }

    public List<User> getUntrustedFollowers() {
        return untrustedFollowers;
    }

    public void setUntrustedFollowers(List<User> untrustedFollowers) {
        this.untrustedFollowers = untrustedFollowers;
    }

    public List<CommunicationType> getCommunicationTypes() {
        return communicationTypes;
    }

    public void setCommunicationTypes(List<CommunicationType> communicationTypes) {
        this.communicationTypes = communicationTypes;
    }

    public Group() {}
}
